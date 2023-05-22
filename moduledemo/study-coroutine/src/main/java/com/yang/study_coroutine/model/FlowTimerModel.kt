package com.yang.study_coroutine.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Timer
import java.util.TimerTask


/**
 * Create by Yang Yang on 2023/5/18
 */
class FlowTimerModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()


    fun startTimer() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                _stateFlow.value += 1
            }
       }, 0, 1000)
    }

}