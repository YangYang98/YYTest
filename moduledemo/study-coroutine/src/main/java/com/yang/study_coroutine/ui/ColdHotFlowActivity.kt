package com.yang.study_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.yang.study_coroutine.R
import com.yang.study_coroutine.databinding.ActivityColdHotFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import com.yang.study_coroutine.utils.log
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class ColdHotFlowActivity : AppCompatActivity() {

    private val coldFlow = flow<Int> {
        (1..5).forEach {
            delay(1000)
            emit(it)
        }
    }

    private val hotFlow = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityColdHotFlowBinding = DataBindingUtil.setContentView(this, R.layout.activity_cold_hot_flow)
        binding.btnColdFlow.setOnClickListener {
            lifecycleScope.launch {
                coldFlow.collect {
                    log("第一个收集器：冷流: $it")
                }
            }

            lifecycleScope.launch {
                delay(5000)
                coldFlow.collect {
                    log("第二个收集器：冷流: $it")
                }
            }
        }

        binding.btnHotFlow.setOnClickListener {
            lifecycleScope.launch {
                launch {
                    hotFlow.collect {
                        log("第一个收集器：热流: $it")
                        delay(100)
                    }
                }

                launch {
                    (1..5).forEach {
                        log("emit pre $it")
                        hotFlow.emit(it)
                        log("emit after $it")
                    }
                }

                //第二个流收集被延迟，晚了100毫秒后就收不到了，想当于不管是否订阅，流都会发送，只管发，而collect1能够收集到是因为他在发送之前进行了订阅收集。
                delay(100)
                launch {
                    hotFlow.collect {
                        log("第二个收集器：热流: $it")
                    }
                }
            }
        }
    }
}