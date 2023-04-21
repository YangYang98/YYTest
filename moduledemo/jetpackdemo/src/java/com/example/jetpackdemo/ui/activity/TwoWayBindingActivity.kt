package java.com.example.jetpackdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityTwoWayBindingBinding
import java.com.example.jetpackdemo.data.User
import java.com.example.jetpackdemo.model.UserViewModel

/**
 * 双向绑定BaseObservable
 */

class TwoWayBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTwoWayBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding)
        binding.userViewModel = UserViewModel(User("yangyang"))

    }
}