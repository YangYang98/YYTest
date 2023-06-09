package com.yang.study_compose.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


/**
 * Create by Yang Yang on 2023/6/9
 */
class CounterViewModel : ViewModel() {
    private val _counter = mutableStateOf(0)
    val counter : State<Int> = _counter

    fun increment() {
        _counter.value = _counter.value + 1
    }

    fun decrement() {
        if (_counter.value > 0) {
            _counter.value = _counter.value - 1
        }
    }
}