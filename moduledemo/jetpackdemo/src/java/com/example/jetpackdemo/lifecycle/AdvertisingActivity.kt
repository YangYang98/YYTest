package java.com.example.jetpackdemo.lifecycle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityAdvertisingBinding
import java.com.example.jetpackdemo.model.AdvertisingViewModel


/**
 * Create by Yang Yang on 2023/8/21
 */
class AdvertisingActivity: AppCompatActivity() {

    private val advertisingViewModel: AdvertisingViewModel by viewModels<AdvertisingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAdvertisingBinding = DataBindingUtil.setContentView(this, R.layout.activity_advertising)

        val advertisingManage = AdvertisingManage(advertisingViewModel)
        lifecycle.addObserver(advertisingManage)
        
        val btnIgnore = binding.tvIgnore

        btnIgnore.setOnClickListener {
            finish()
        }

        advertisingViewModel._timingResult.observe(this) {
            btnIgnore.text = "广告剩余${it}秒"

            if (it == 0L) {
                finish()
            }
        }
    }
}