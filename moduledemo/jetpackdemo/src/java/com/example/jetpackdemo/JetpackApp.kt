package java.com.example.jetpackdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp
import java.com.example.jetpackdemo.data.hilt.TestHilt
import javax.inject.Inject


/**
 * Create by Yang Yang on 2023/8/24
 */
@HiltAndroidApp
class JetpackApp: Application() {

    @Inject
    lateinit var testHilt: TestHilt

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())

        Log.e("YANGYANG", "inject result:${testHilt.name}")
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