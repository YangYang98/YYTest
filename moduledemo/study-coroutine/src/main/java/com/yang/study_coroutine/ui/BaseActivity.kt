package com.yang.study_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import com.yang.study_coroutine.interfaces.CoroutineScopeInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


/**
 * Create by Yang Yang on 2023/5/15
 */
abstract class BaseActivity: AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

abstract class BaseActivity2: AppCompatActivity(), CoroutineScopeInterface {

}