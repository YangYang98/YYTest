package java.com.example.jetpackdemo.listener

import android.content.Context
import android.content.Intent
import android.view.View
import java.com.example.jetpackdemo.ui.activity.navigation.NavigationMainElementActivity


/**
 * Create by Yang Yang on 2023/4/23
 */
class NavigationEventHandleListener constructor(val context: Context) {

    fun btnMainElementOnCLick(view: View) {
        context.startActivity(Intent(context, NavigationMainElementActivity::class.java))
    }
}