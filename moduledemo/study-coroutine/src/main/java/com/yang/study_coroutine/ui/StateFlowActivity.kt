package com.yang.study_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.yang.study_coroutine.R
import com.yang.study_coroutine.utils.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch

class StateFlowActivity : AppCompatActivity() {

    private val etFlow = MutableStateFlow("et_Hint")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow)

        //打印协程名称
        System.setProperty("kotlinx.coroutines.debug", "on")
        val et = findViewById<EditText>(R.id.et)
        et.doAfterTextChanged {
            if (!TextUtils.isEmpty(it)) {
                etFlow.value = it.toString()
            }
        }

        lifecycleScope.launch {
            //在1秒采样周期内只发射最新的值
            etFlow.sample(1000).collect {
                //获取值去搜索
                log(it)
            }
        }

    }
}