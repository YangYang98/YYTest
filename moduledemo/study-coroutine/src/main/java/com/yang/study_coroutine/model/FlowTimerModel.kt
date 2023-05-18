package com.yang.study_coroutine.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow


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

}