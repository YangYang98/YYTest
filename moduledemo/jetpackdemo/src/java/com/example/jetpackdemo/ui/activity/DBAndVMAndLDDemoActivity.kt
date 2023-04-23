package java.com.example.jetpackdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityDbandVmandLddemoBinding
import java.com.example.jetpackdemo.model.DBAndVMAndLDDemoViewModel

class DBAndVMAndLDDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDbandVmandLddemoBinding = DataBindingUtil.setContentView(this, R.layout.activity_dband_vmand_lddemo)

        //TODO 重要‼️
        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[DBAndVMAndLDDemoViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}