package com.example.changeskin

import android.os.Environment
import java.io.File


/**
 * Create by Yang Yang on 2023/4/14
 */
class Config {

    companion object {
        val PATH =
            Environment.getExternalStorageDirectory().absolutePath + File.separator + "themeRes-debug.apk"
    }
}