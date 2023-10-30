package java.com.example.jetpackdemo.data.hilt

import android.util.Log
import javax.inject.Inject


/**
 * Create by Yang Yang on 2023/10/30
 */
class TestHilt @Inject constructor(): ITestHilt{

    val name = "TestHilt"

    override fun printName() {
        Log.e("YANGYANG", "name is:${name}")
    }
}