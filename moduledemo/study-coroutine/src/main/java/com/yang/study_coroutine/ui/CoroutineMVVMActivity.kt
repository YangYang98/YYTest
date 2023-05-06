package com.yang.study_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yang.study_coroutine.R
import com.yang.study_coroutine.databinding.ActivityCoroutineMvvmactivityBinding
import com.yang.study_coroutine.model.ApiViewModel

class CoroutineMVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCoroutineMvvmactivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine_mvvmactivity)
        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory())[ApiViewModel::class.java]
        binding.model = viewModel

        binding.btnRequest.setOnClickListener {
            viewModel.requestLogin("Derry-vip", "123456")
        }

    }
}