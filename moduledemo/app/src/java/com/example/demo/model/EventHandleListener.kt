package java.com.example.demo.model

import android.content.Context
import android.view.View
import android.widget.Toast


/**
 * Create by Yang Yang on 2023/4/6
 */
class EventHandleListener(val context: Context) {

    fun buttonOnClick(view: View) {
        Toast.makeText(context, "buttonOnClick", Toast.LENGTH_LONG).show()
    }
}