package java.com.example.jetpackdemo.listener

import android.content.Context
import android.content.Intent
import android.view.View
import java.com.example.jetpackdemo.ui.activity.*
import java.com.example.jetpackdemo.ui.activity.room.RoomBaseActivity


/**
 * Create by Yang Yang on 2023/4/19
 */
class EventHandleListener constructor(val context: Context) {

    fun btnDataBindingBaseOnClick(view: View) {

        context.startActivity(Intent(context, DataBindingBaseActivity::class.java))
    }

    fun btnTwoWayBindingOnClick(view: View) {
        context.startActivity(Intent(context, TwoWayBindingActivity::class.java))
    }

    fun btnTwoWayBinding2OnClick(view: View) {
        context.startActivity(Intent(context, TwoWayBinding2Activity::class.java))
    }

    fun btnRecycleViewBindingOnClick(view: View) {
        context.startActivity(Intent(context, RecycleViewBindingActivity::class.java))
    }

    fun btnDBAndVMAndLDOnClick(view: View) {
        context.startActivity(Intent(context, DBAndVMAndLDDemoActivity::class.java))
    }

    fun btnRoomBaseOnClick(view: View) {
        context.startActivity(Intent(context, RoomBaseActivity::class.java))
    }
}