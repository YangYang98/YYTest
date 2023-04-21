package java.com.example.jetpackdemo.listener

import android.content.Context
import android.content.Intent
import android.view.View
import java.com.example.jetpackdemo.ui.activity.DataBindingBaseActivity
import java.com.example.jetpackdemo.ui.activity.TwoWayBindingActivity


/**
 * Create by Yang Yang on 2023/4/19
 */
class EventHandleListener constructor(val context: Context) {

    fun btnOnClick(view: View) {

        context.startActivity(Intent(context, DataBindingBaseActivity::class.java))
    }

    fun btnTwoWayBindingOnClick(view: View) {
        context.startActivity(Intent(context, TwoWayBindingActivity::class.java))
    }
}