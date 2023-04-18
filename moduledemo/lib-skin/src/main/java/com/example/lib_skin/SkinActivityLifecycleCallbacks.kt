package com.example.lib_skin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.ArrayMap
import android.view.LayoutInflater
import java.util.Observable


/**
 * Create by Yang Yang on 2023/4/13
 */
class SkinActivityLifecycleCallbacks(private val observable: Observable) : Application.ActivityLifecycleCallbacks {

    private val skinFactoryCache = ArrayMap<Activity, SkinLayoutInflaterFactory>()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (!skinFactoryCache.containsKey(activity)) {
            val skinLayoutInflaterFactory  = forceSetFactory2(activity.layoutInflater, activity)
            skinFactoryCache[activity] = skinLayoutInflaterFactory
        }
        val skinLayoutInflaterFactory = skinFactoryCache[activity]
        observable.addObserver(skinLayoutInflaterFactory)
    }

    override fun onActivityStarted(activity: Activity) {
        tryInitSkin(activity)
    }

    private fun tryInitSkin(activity: Activity) {
        val skinState = SkinSharedPreferences.getInstance().getString(SkinConfig.SP_SKIN_STATE_NAME)
        if (!skinState.isNullOrEmpty()) {
            val skinPath = SkinSharedPreferences.getInstance().getString(SkinConfig.SP_SKIN_PATH)
            if (!skinPath.isNullOrEmpty() || SkinManager.State.SKIN.name == skinState) {
                SkinManager.getInstance().loadSkin(skinPath, true, activity)
            }
        }
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
        val skinLayoutInflaterFactory = skinFactoryCache[activity]
        if (skinLayoutInflaterFactory != null) {
            skinLayoutInflaterFactory.removeKey(activity)
            observable.deleteObserver(skinLayoutInflaterFactory)
        }
        skinFactoryCache.remove(activity)
    }

    private fun forceSetFactory2(inflater: LayoutInflater, activity: Activity): SkinLayoutInflaterFactory? {
        val inflaterClass = LayoutInflater::class.java

        try {
            val factory2 = inflaterClass.getDeclaredField("mFactory2")
            factory2.isAccessible = true
            val factory = inflaterClass.getDeclaredField("mFactory")
            factory.isAccessible = true

            val skinLayoutInflaterFactory = SkinLayoutInflaterFactory(activity)

            factory2.set(inflater, skinLayoutInflaterFactory)
            factory.set(inflater, skinLayoutInflaterFactory)
            return skinLayoutInflaterFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}