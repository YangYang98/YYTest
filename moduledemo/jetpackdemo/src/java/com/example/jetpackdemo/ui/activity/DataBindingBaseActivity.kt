package java.com.example.jetpackdemo.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityDataBindingBaseBinding
import java.com.example.jetpackdemo.data.Config
import java.com.example.jetpackdemo.listener.EventHandleListener

class DataBindingBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDataBindingBaseBinding: ActivityDataBindingBaseBinding
            = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_base)
        activityDataBindingBaseBinding.config = Config()
        activityDataBindingBaseBinding.eventHandle = EventHandleListener(this)

        activityDataBindingBaseBinding.btnDataBindingAdapter.setOnClickListener {
            startActivity(Intent(this, DataBindingAdapterActivity::class.java))
        }
    }
}