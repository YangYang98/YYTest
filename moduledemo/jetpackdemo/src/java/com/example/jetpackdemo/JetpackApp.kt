package java.com.example.jetpackdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner


/**
 * Create by Yang Yang on 2023/8/24
 */
class JetpackApp: Application() {

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
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