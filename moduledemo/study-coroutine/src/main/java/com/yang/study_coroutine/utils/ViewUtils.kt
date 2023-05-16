package com.yang.study_coroutine.utils

import android.os.Build
import android.view.View
import android.view.View.OnAttachStateChangeListener
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/**
 * Create by Yang Yang on 2023/5/16
 */

/**
 * 确保协程及时被取消的onClickListener
 */
fun View.onClickAutoDisposable(
    context: CoroutineContext = Dispatchers.Main,
    handler: suspend CoroutineScope.(v: View) -> Unit
) {
    setOnClickListener { v ->
        GlobalScope.launch(context, CoroutineStart.DEFAULT) {
            handler(v)
        }.asAutoDisposable(v)
    }
}

fun Job.asAutoDisposable(view: View) = AutoDisposableJob(view, this)

class AutoDisposableJob(private val view: View, private val wrapped: Job)
    :Job by wrapped, OnAttachStateChangeListener {
    override fun onViewAttachedToWindow(v: View) = Unit

    override fun onViewDetachedFromWindow(v: View) {
        cancel()
        view.removeOnAttachStateChangeListener(this)
    }

    private fun isViewAttached() =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view.isAttachedToWindow || view.windowToken != null


    init {
        if (isViewAttached()) {
            view.addOnAttachStateChangeListener(this)
        } else {
            cancel()
        }

        invokeOnCompletion {
            view.removeOnAttachStateChangeListener(this)
        }
    }

}