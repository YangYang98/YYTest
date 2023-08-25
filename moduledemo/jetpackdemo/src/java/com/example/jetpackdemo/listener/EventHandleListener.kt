package java.com.example.jetpackdemo.listener

import android.content.Context
import android.content.Intent
import android.view.View
import java.com.example.jetpackdemo.lifecycle.LifecycleBaseActivity
import java.com.example.jetpackdemo.livedata.LiveDataBaseActivity
import java.com.example.jetpackdemo.ui.activity.DBAndVMAndLDDemoActivity
import java.com.example.jetpackdemo.ui.activity.DataBindingBaseActivity
import java.com.example.jetpackdemo.ui.activity.RecycleViewBindingActivity
import java.com.example.jetpackdemo.ui.activity.TwoWayBinding2Activity
import java.com.example.jetpackdemo.ui.activity.TwoWayBindingActivity
import java.com.example.jetpackdemo.ui.activity.compose.ComposeBaseActivity
import java.com.example.jetpackdemo.ui.activity.navigation.NavigationBaseActivity
import java.com.example.jetpackdemo.ui.activity.paging.PagingBaseActivity
import java.com.example.jetpackdemo.ui.activity.room.RoomBaseActivity
import java.com.example.jetpackdemo.ui.activity.wrokmanager.WorkManagerBaseActivity


/**
 * Create by Yang Yang on 2023/4/19
 */
class EventHandleListener constructor(val context: Context) {

    fun btnLifecycleBaseOnClick(view: View) {
        context.startActivity(Intent(context, LifecycleBaseActivity::class.java))
    }

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

    fun btnNavigationBaseOnClick(view: View) {
        context.startActivity(Intent(context, NavigationBaseActivity::class.java))
    }

    fun btnComposeStudyOnClick(view: View) {
        context.startActivity(Intent(context, ComposeBaseActivity::class.java))
    }

    fun btnWorkManagerOnClick(view: View) {
        context.startActivity(Intent(context, WorkManagerBaseActivity::class.java))
    }

    fun btnPagingOnClick(view: View) {
        context.startActivity(Intent(context, PagingBaseActivity::class.java))
    }

    fun btnLiveDataOnClick(view: View) {
        context.startActivity(Intent(context, LiveDataBaseActivity::class.java))
    }
}