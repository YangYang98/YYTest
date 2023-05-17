package com.yang.study_coroutine.utils

import android.util.Log


/**
 * Create by Yang Yang on 2023/5/17
 */

fun log(msg: String) {
    Log.d("LOG_PRINT", "内容:$msg 线程:${Thread.currentThread().name}")
}