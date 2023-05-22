package com.yang.study_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yang.study_coroutine.R
import com.yang.study_coroutine.databinding.ActivityFlowTimerBinding
import com.yang.study_coroutine.model.FlowTimerModel
import kotlinx.coroutines.launch

class FlowTimerActivity : AppCompatActivity() {


    private val mainViewModel by viewModels<FlowTimerModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFlowTimerBinding = DataBindingUtil.setContentView(this, R.layout.activity_flow_timer)

        val textView = findViewById<TextView>(R.id.text_view)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.stateFlow.collect { time ->
                    textView.text = time.toString()
                }
            }
        }
    }
}