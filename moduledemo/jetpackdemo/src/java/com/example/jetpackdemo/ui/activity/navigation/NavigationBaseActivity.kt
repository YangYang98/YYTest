package java.com.example.jetpackdemo.ui.activity.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityNavigationBaseBinding
import java.com.example.jetpackdemo.listener.NavigationEventHandleListener

class NavigationBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNavigationBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_base)
        binding.eventHandle = NavigationEventHandleListener(this)
    }
}