package com.example.changeskin

import android.app.Application
import com.example.lib_skin.SkinManager


/**
 * Create by Yang Yang on 2023/4/13
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
    }
}