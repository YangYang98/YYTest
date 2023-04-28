package java.com.example.jetpackdemo.ui.activity.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityNavigationMainElementBinding


/**
 * Create by Yang Yang on 2023/4/28
 */
class NavigationMainElementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNavigationMainElementBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_main_element)

        /**
         * 指定id为fragmentContainerView的Fragment的管理、切换通过controller来进行
         */
        val controller = Navigation.findNavController(this, R.id.fragmentContainerView)
        NavigationUI.setupActionBarWithNavController(this, controller)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        return navController.navigateUp()
    }
}