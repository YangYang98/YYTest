package com.example.lib_skin

import android.app.Application
import android.content.pm.PackageManager
import android.widget.Toast
import java.io.File


/**
 * Create by Yang Yang on 2023/4/13
 */
class SkinCheckApk {

    companion object {

        @JvmStatic
        fun checkPath(application: Application, path: String?): Boolean {
            if (path.isNullOrEmpty()) {
                showErrorInfo(application, "皮肤路径为空")
                return false
            }

            val file = File(path)
            if (!file.exists()) {
                showErrorInfo(application, "皮肤不存在")
                return false
            }

            val packName = getPackName(application, path)
            if (packName.isNullOrEmpty()) {
                showErrorInfo(application, "无法获取到包名")
                return false
            }

            return true

        }

        private fun getPackName(application: Application, path: String?): String? {
            if (!path.isNullOrEmpty()) {
                val info = application.packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)
                if (info != null) {
                    val appInfo = info.applicationInfo
                    return appInfo.packageName
                }
            }
            return null
        }

        private fun showErrorInfo(application: Application, msg: String) {
            Toast.makeText(application, msg, Toast.LENGTH_SHORT).show()
        }

    }
}