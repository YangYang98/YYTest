package com.yang.study_coroutine.impl

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.yang.study_coroutine.interfaces.CoroutineScopeInterface


/**
 * Create by Yang Yang on 2023/5/16
 */
class ActivityLifecycleCallbackImpl: ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        (activity as? CoroutineScopeInterface)?.createScope()
    }

    override fun onActivityStarted(activity: Activity) {
        
    }

    override fun onActivityResumed(activity: Activity) {
        
    }

    override fun onActivityPaused(activity: Activity) {
        
    }

    override fun onActivityStopped(activity: Activity) {
        
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        
    }

    override fun onActivityDestroyed(activity: Activity) {
        (activity as? CoroutineScopeInterface)?.destroyScope()
    }
}