package com.yang.study_coroutine.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yang.study_coroutine.R
import com.yang.study_coroutine.databinding.ActivityFlowBinding

class FlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFlowBinding = DataBindingUtil.setContentView(this, R.layout.activity_flow)

        binding.btnColdHotFlow.setOnClickListener {
            startActivity(Intent(this, ColdHotFlowActivity::class.java))
        }
        binding.btnStateFlow.setOnClickListener {
            startActivity(Intent(this, StateFlowActivity::class.java))
        }
        binding.btnFlowTimer.setOnClickListener {
            startActivity(Intent(this, FlowTimerActivity::class.java))
        }
    }
}