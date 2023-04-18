package com.example.lib_skin

import android.app.Application
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import androidx.core.content.ContextCompat
import com.example.lib_skin.data.SkinResourceCacheBean


/**
 * Create by Yang Yang on 2023/4/13
 */
class SkinResource(private val application: Application, private val path: String) {

    private var resources: Resources

    init {
        resources = createSkinResource(path)
    }

    companion object {
        private var skinResource: SkinResource? = null
        private val resourceIdCache: HashMap<String, SkinResourceCacheBean> = HashMap()

        private const val COLOR = "color"
        private const val STRING = "string"
        private const val DRAWABLE = "drawable"
        private const val DIMEN = "dimen"

        @JvmStatic
        fun init(application: Application, path: String, isNewPath: Boolean) {
            if (skinResource == null || isNewPath) {
                synchronized(SkinResource::class.java) {
                    if (skinResource == null || isNewPath) {
                        skinResource = SkinResource(application, path)
                    }
                }
            }
        }

        @JvmStatic
        fun getInstance(): SkinResource {
            if (skinResource == null) {
                throw RuntimeException("SkinResource没有初始化 errorCode:${SkinConfig.SKIN_ERROR_5}")
            }
            clearSkin()
            return skinResource!!
        }

        private fun clearSkin() {
            val keys = mutableListOf<String>()

            resourceIdCache.keys.forEach {
                val value = resourceIdCache[it]
                if (value != null && value.type == SkinResourceCacheBean.Type.SKIN) {
                    keys.add(it)
                }
            }
            keys.forEach {
                resourceIdCache.remove(it)
            }
        }
    }

    private fun createSkinResource(path: String): Resources {
        try {
            val assetManager = AssetManager::class.java.newInstance()
            val addAssetPath = "addAssetPath"
            val method = assetManager::class.java.getDeclaredMethod(addAssetPath, String::class.java)
            method.isAccessible = true
            method.invoke(assetManager, path)
            return Resources(assetManager, createDisplayMetrics(), createConfiguration())
        } catch (e: Exception) {
            throw RuntimeException("Resources创建失败 errorCode${SkinConfig.SKIN_ERROR_4}")
        }
    }

    private fun createDisplayMetrics(): DisplayMetrics {
        return application.resources.displayMetrics
    }

    private fun createConfiguration(): Configuration {
        return application.resources.configuration
    }

    fun reset() {
        resources = application.resources
    }

    /**
     * 获取当前apk包名
     */
    private fun getPackName(): String? {
        return getPackName(null)
    }

    private fun getPackName(path: String?): String? {
        if (!path.isNullOrEmpty()) {
            val info = application.packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)
            if (info != null) {
                val appInfo = info.applicationInfo
                return appInfo.packageName
            }
        }
        return application.packageName
    }

    private fun getSystemResourceId(value: String, deftype: String): Int {
        return application.resources.getIdentifier(value, deftype, getPackName())
    }

    private fun getKeyIdCache(key: String, defType: String): String {
        return "$defType:$key:${SkinManager.getInstance().skinState.name}"
    }

    private fun cacheResourceId(key: String, defType: String, value: SkinResourceCacheBean) {
        val resultKey = getKeyIdCache(key, defType)
        if (!resourceIdCache.containsKey(resultKey)) {
            resourceIdCache[resultKey] = value
        }
    }

    private fun getCacheResourceId(value: String, defType: String): SkinResourceCacheBean? {
        return resourceIdCache[getKeyIdCache(value, defType)]
    }

    private fun throwRuntimeException(type: String) {
        throw RuntimeException("皮肤包和本地资源都没有当前属性(${type}) errorCode:${SkinConfig.SKIN_ERROR_6}")
    }

    //@RequiresApi(Build.VERSION_CODES.M)
    fun getColor(value: String): Int {
        // 尝试在缓存中获取颜色资源
        val cacheColorId = tryGetCacheResourceColorId(value)
        if (cacheColorId != SkinConfig.SKIN_FAIL) {
            return cacheColorId
        }
        // 先获取皮肤包中的id
        var colorId = resources.getIdentifier(value, COLOR, getPackName(path))

        // id == 0 表示皮肤包中没有该资源
        if (colorId == 0) {
            // 获取本地的资源
            colorId = getSystemResourceId(value, COLOR)

            // 如果本地资源没有获取到，尝试吧这个参数当作系统资源再次获取
            if (colorId == 0) {
                colorId = getSystemResourceId("android:$value", COLOR)
            }

            if (colorId != 0) {
                cacheResourceId(value, COLOR, SkinResourceCacheBean(colorId, SkinResourceCacheBean.Type.SYSTEM))
                return ContextCompat.getColor(application, colorId)
            }
            throwRuntimeException(COLOR)
        }
        cacheResourceId(value, COLOR, SkinResourceCacheBean(colorId, SkinResourceCacheBean.Type.SKIN))
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(colorId, null)
        } else {
            resources.getColor(colorId)
        }
    }

    @Synchronized
    private fun tryGetCacheResourceColorId(value: String): Int {
        val cacheData = getCacheResourceId(value, COLOR)
        if (cacheData != null) {
            val resourceId = cacheData.resourceId
            val type = cacheData.type
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (type == SkinResourceCacheBean.Type.SKIN) {
                    return resources.getColor(resourceId, null)
                } else if (type == SkinResourceCacheBean.Type.SYSTEM) {
                    return application.getColor(resourceId)
                }
            } else {
                if (type == SkinResourceCacheBean.Type.SKIN) {
                    return resources.getColor(resourceId)
                } else if (type == SkinResourceCacheBean.Type.SYSTEM) {
                    //return application.getColor(resourceId)
                    SkinConfig.SKIN_FAIL
                }
            }
        }
        return SkinConfig.SKIN_FAIL
    }

    fun getString(value: String): String {
        val cacheValue = tryGetCacheResourceStringId(value)
        if (cacheValue != null) {
            return cacheValue
        }
        var id = resources.getIdentifier(value, STRING, getPackName(path))
        if (id == 0) {
            id = getSystemResourceId(value, STRING)
            if (id == 0) {
                id = getSystemResourceId("android:$value", STRING)
            }
            if (id != 0) {
                cacheResourceId(value, STRING, SkinResourceCacheBean(id, SkinResourceCacheBean.Type.SYSTEM))
                return application.getString(id)
            }

            throwRuntimeException(STRING)
        }
        cacheResourceId(value, STRING, SkinResourceCacheBean(id, SkinResourceCacheBean.Type.SKIN))
        return resources.getString(id)
    }

    private fun tryGetCacheResourceStringId(value: String): String? {
        val cacheData = getCacheResourceId(value, STRING)
        if (cacheData != null) {
            val resourceId = cacheData.resourceId
            val type = cacheData.type
            if (type == SkinResourceCacheBean.Type.SKIN) {
                return resources.getString(resourceId)
            } else if (type == SkinResourceCacheBean.Type.SYSTEM) {
                return application.getString(resourceId)
            }
        }
        return null
    }

    fun getDrawable(value: String): Drawable? {
        val cacheValue = tryGetCacheResourceDrawableId(value)
        if (cacheValue != null) {
            return cacheValue
        }
        var id = resources.getIdentifier(value, DRAWABLE, getPackName(path))
        if (id == 0) {
            id = getSystemResourceId(value, DRAWABLE)
            if (id == 0) {
                id = getSystemResourceId("android:$value", DRAWABLE)
            }
            if (id != 0) {
                cacheResourceId(value, DRAWABLE, SkinResourceCacheBean(id, SkinResourceCacheBean.Type.SYSTEM))
                return ContextCompat.getDrawable(application, id)
            }

            throwRuntimeException(DRAWABLE)
        }
        cacheResourceId(value, DRAWABLE, SkinResourceCacheBean(id, SkinResourceCacheBean.Type.SKIN))
        return resources.getDrawable(id, null)
    }

    private fun tryGetCacheResourceDrawableId(value: String): Drawable? {
        val cacheData = getCacheResourceId(value, DRAWABLE)
        if (cacheData != null) {
            val resourceId = cacheData.resourceId
            val type = cacheData.type
            if (type == SkinResourceCacheBean.Type.SKIN) {
                return resources.getDrawable(resourceId, null)
            } else if (type == SkinResourceCacheBean.Type.SYSTEM) {
                return ContextCompat.getDrawable(application, resourceId)
            }
        }
        return null
    }

    fun getFontSize(value: String): Float {
        val cacheValue = tryGetCacheResourceFontSizeId(value)
        if (cacheValue != SkinConfig.SKIN_FAIL_FLOAT) {
            return cacheValue
        }

        var id = resources.getIdentifier(value, DIMEN, getPackName(path))
        if (id == 0) {
            id = getSystemResourceId(value, DIMEN)

            if (id == 0) {
                id = getSystemResourceId("android:$value", DIMEN)
            }
            if (id != 0) {
                cacheResourceId(value, DIMEN, SkinResourceCacheBean(id, SkinResourceCacheBean.Type.SYSTEM))
                return application.resources.getDimension(id)
            }
            throwRuntimeException(DIMEN)
        }
        cacheResourceId(value, DIMEN, SkinResourceCacheBean(id, SkinResourceCacheBean.Type.SKIN))
        return resources.getDimension(id)
    }

    private fun tryGetCacheResourceFontSizeId(value: String): Float {
        val cacheData = getCacheResourceId(value, DIMEN)
        if (cacheData != null) {
            val resourceId = cacheData.resourceId
            val type = cacheData.type
            if (type == SkinResourceCacheBean.Type.SKIN) {
                return resources.getDimension(resourceId)
            } else if (type == SkinResourceCacheBean.Type.SYSTEM) {
                return application.resources.getDimension(resourceId)
            }
        }
        return SkinConfig.SKIN_FAIL_FLOAT
    }

    fun getIdentifier(value: String, defType: String): Int {
        var id = resources.getIdentifier(value, defType, getPackName(path))
        if (id == 0) {
            id = getSystemResourceId(value, defType)
        }
        return id
    }

}