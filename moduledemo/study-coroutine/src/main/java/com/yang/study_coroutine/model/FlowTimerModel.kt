package com.yang.study_coroutine.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


/**
 * Create by Yang Yang on 2023/5/18
 */
class FlowTimerModel : ViewModel() {

    val timeFlow = flow {
        var time = 0
        while (true) {
            emit(time)
            delay(1000)
            time++
        }
    }

    // 第二个参数：超时机制
    //因为横竖屏切换通常很快就能完成，这里我们通过stateIn函数的第2个参数指定了一个5秒的超时时长，那么只要在5秒钟内横竖屏切换完成了，Flow就不会停止工作。
    //反过来讲，这也使得程序切到后台之后，如果5秒钟之内再回到前台，那么Flow也不会停止工作。但是如果切到后台超过了5秒钟，Flow就会全部停止了。
    val stateFlow = timeFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

}