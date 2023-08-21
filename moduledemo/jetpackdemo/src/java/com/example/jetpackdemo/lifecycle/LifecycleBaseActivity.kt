package java.com.example.jetpackdemo.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityLifecycleBaseBinding


/**
 * Create by Yang Yang on 2023/8/21
 */
class LifecycleBaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLifecycleBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle_base)
        binding.eventHandle = LifecycleEventHandleListener(this)
    }
}