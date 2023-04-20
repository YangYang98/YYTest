package java.com.example.jetpackdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityDataBindingAdapterBinding

class DataBindingAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindingAdapterBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_adapter)
        binding.networkImage = "https://img1.baidu.com/it/u=3007048469,3759326707&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1682096400&t=6da503016a91a66d68e5ce50c7d63c37"
        //binding.localImage = R.drawable.ic_launcher_background
        //binding.
    }
}