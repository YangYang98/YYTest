package com.example.lib_skin

import android.app.Application
import android.content.Context
import android.content.SharedPreferences


/**
 * Create by Yang Yang on 2023/4/14
 */
class SkinSharedPreferences constructor(private val application: Application) {

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(SkinConfig.SP_NAME, Context.MODE_PRIVATE)

    companion object {
        private var skinSharedPreferences: SkinSharedPreferences? = null


        @JvmStatic
        fun init(application: Application) {
            if (skinSharedPreferences == null) {
                synchronized(SkinSharedPreferences::class.java) {
                    if (skinSharedPreferences == null) {
                        skinSharedPreferences = SkinSharedPreferences(application)
                    }
                }
            }
        }

        @JvmStatic
        fun getInstance(): SkinSharedPreferences {
            if (skinSharedPreferences == null) {
                throw RuntimeException("SkinSharedPreferences没有初始化 errorCode:${SkinConfig.SKIN_ERROR_3}")
            }
            return skinSharedPreferences!!
        }
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }
}