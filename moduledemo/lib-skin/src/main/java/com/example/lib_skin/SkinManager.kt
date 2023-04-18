package com.example.lib_skin

import android.app.Activity
import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.lib_skin.data.SkinResourceCacheBean
import java.util.*


/**
 * Create by Yang Yang on 2023/4/13
 */
class SkinManager(private val application: Application) : Observable() {

    private var skinResource: SkinResource? = null
    var skinState = State.ORIGIN

    init {
        application.registerActivityLifecycleCallbacks(SkinActivityLifecycleCallbacks(this))
        SkinSharedPreferences.init(application)
    }

    companion object {
        private var skinManager: SkinManager? = null

        private const val COLOR = "color"
        private const val STRING = "string"
        private const val DRAWABLE = "drawable"
        private const val DIMEN = "dimen"

        @JvmStatic
        fun init(application: Application) {
            if (skinManager == null) {
                synchronized(SkinManager::class.java) {
                    if (skinManager == null) {
                        skinManager = SkinManager(application)
                    }
                }
            }
        }

        @JvmStatic
        fun getInstance(): SkinManager {
            if (skinManager == null) {
                throw RuntimeException("SkinManager没有初始化 errorCode:${SkinConfig.SKIN_ERROR_1}")
            }
            return skinManager!!
        }
    }

    fun loadSkin(path: String?, activity: Activity) {
        if (path.isNullOrEmpty()) {
            reset()
            return
        }
        loadSkin(path, false, activity)
    }

    //@param isNotify: 是否强制刷新
    fun loadSkin(path: String?, isNotify: Boolean, activity: Activity) {
        if (!checkResourcePath(path)) {
            return
        }

        val oldPath = SkinSharedPreferences.getInstance().getString(SkinConfig.SP_SKIN_PATH)
        val isNewPath = !oldPath.equals(path)

        if (skinResource == null || isNewPath) {
            SkinResource.init(application, path!!, isNewPath)
            skinResource = SkinResource.getInstance()
        }

        //TODO 干嘛用的？
        //skinResource.resume();

        if (skinState == State.ORIGIN || isNotify || isNewPath) {
            skinState = State.SKIN
            notifyChanged(activity)

            SkinSharedPreferences.getInstance().setString(SkinConfig.SP_SKIN_STATE_NAME, State.SKIN.name)
            SkinSharedPreferences.getInstance().setString(SkinConfig.SP_SKIN_PATH, path ?: "")
        }
    }

    private fun checkResourcePath(path: String?): Boolean {
        return SkinCheckApk.checkPath(application, path)
    }

    fun reset() {
        if (skinState == State.SKIN) {
            skinState = State.ORIGIN

            if (skinResource != null) {
                skinResource!!.reset()
            }
            notifyChanged(null)

            SkinSharedPreferences.getInstance().setString(SkinConfig.SP_SKIN_STATE_NAME, SkinResourceCacheBean.Type.SYSTEM.name)
            SkinSharedPreferences.getInstance().setString(SkinConfig.SP_SKIN_PATH, "")
        }
    }

    private fun notifyChanged(activity: Activity?) {
        setChanged()
        notifyObservers(activity)
    }

    fun getSystemResourceId(value: String, defType: String): Int {
        val identifier = application.resources.getIdentifier(value, defType, application.packageName)
        if (identifier == 0) {
            throw RuntimeException("获取系统资源失败，请检查资源文件下是否存在:${value}(SkinManager) errorCode:${SkinConfig.SKIN_ERROR_8}")
        }
        return identifier
    }

    fun getColor(value: String): Int {
        if (skinResource != null) {
            return skinResource!!.getColor(value)
        }
        return ContextCompat.getColor(application, getSystemResourceId(value, COLOR))
    }

    fun getString(value: String): String {
        if (skinResource != null) {
            return skinResource!!.getString(value)
        }
        return application.resources.getString(getSystemResourceId(value, STRING))
    }

    fun getDrawable(value: String): Drawable? {
        if (skinResource != null) {
            return skinResource!!.getDrawable(value)
        }
        return ContextCompat.getDrawable(application, getSystemResourceId(value, DRAWABLE))
    }

    fun getFontSize(value: String): Float {
        if (skinResource != null) {
            return skinResource!!.getFontSize(value)
        }
        return application.resources.getDimension(getSystemResourceId(value, DIMEN))
    }

    fun getIdentifier(value: String, defType: String): Int {
        if (skinResource != null) {
            return skinResource!!.getIdentifier(value, defType)
        }
        return application.resources.getIdentifier(value, defType, application.packageName)
    }

    enum class State {
        SKIN, ORIGIN
    }
}