package com.yang.study_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.yang.study_coroutine.R
import com.yang.study_coroutine.databinding.ActivityFlowTimerBinding
import com.yang.study_coroutine.model.FlowTimerModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowTimerActivity : AppCompatActivity() {


    private val mainViewModel by viewModels<FlowTimerModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFlowTimerBinding = DataBindingUtil.setContentView(this, R.layout.activity_flow_timer)

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.timeFlow.collectLatest {
                    binding.textView.text = it.toString()
                    delay(3000)
                }
            }
        }
    }
}