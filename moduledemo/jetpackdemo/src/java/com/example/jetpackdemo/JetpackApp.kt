package java.com.example.jetpackdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp
import java.com.example.jetpackdemo.data.hilt.ITestHilt
import java.com.example.jetpackdemo.data.hilt.TestHilt2
import javax.inject.Inject


/**
 * Create by Yang Yang on 2023/8/24
 */
@HiltAndroidApp
class JetpackApp: Application() {

    @Inject
    lateinit var testHilt: ITestHilt

    @Inject
    lateinit var testHilt2: TestHilt2

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())

        testHilt.printName()

        testHilt2.printName()
    }

    inner class ApplicationLifecycleObserver: DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            onAppForeground()
        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            onAppBackground()
        }

        private fun onAppForeground() {
            Log.e("JetpackApp", "ApplicationObserver: app moved to foreground")
        }

        private fun onAppBackground() {
            Log.e("JetpackApp", "ApplicationObserver: app moved to background")
        }
    }
}