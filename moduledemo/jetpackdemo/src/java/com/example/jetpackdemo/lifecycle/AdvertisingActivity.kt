package java.com.example.jetpackdemo.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityAdvertisingBinding


/**
 * Create by Yang Yang on 2023/8/21
 */
class AdvertisingActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAdvertisingBinding = DataBindingUtil.setContentView(this, R.layout.activity_advertising)

        val advertisingManage = AdvertisingManage()
        lifecycle.addObserver(advertisingManage)
        
        val btnIgnore = binding.tvIgnore
        advertisingManage.advertisingManageListener = object : AdvertisingManage.AdvertisingManageListener {
            override fun timing(second: Int) {
                
            }

            override fun enterMainActivity() {
                finish()
            }
        }

        btnIgnore.setOnClickListener {
            finish()
        }
    }
}