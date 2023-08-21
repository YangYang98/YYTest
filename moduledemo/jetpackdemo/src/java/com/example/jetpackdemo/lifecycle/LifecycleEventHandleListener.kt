package java.com.example.jetpackdemo.lifecycle

import android.content.Context
import android.content.Intent
import android.view.View


/**
 * Create by Yang Yang on 2023/8/21
 */
class LifecycleEventHandleListener constructor(val context: Context) {

    fun btnAdvertisingOnCLick(view: View) {
        context.startActivity(Intent(context, AdvertisingActivity::class.java))
    }
}