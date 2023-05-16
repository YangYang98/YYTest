package com.yang.study_coroutine.impl

import android.app.Application


/**
 * Create by Yang Yang on 2023/5/16
 */
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
    }
}