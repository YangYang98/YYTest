package java.com.example.jetpackdemo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityTwoWayBinding2Binding
import java.com.example.jetpackdemo.data.User
import java.com.example.jetpackdemo.model.UserViewModel2

/**
 * 双向绑定ObservableField
 */

class TwoWayBinding2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTwoWayBinding2Binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding2)
        binding.userViewModel = UserViewModel2(User("yangyang"))
    }
}