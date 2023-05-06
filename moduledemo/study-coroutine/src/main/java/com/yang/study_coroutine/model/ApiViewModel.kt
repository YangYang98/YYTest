package com.yang.study_coroutine.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yang.study_coroutine.data.LoginRegisterResponse
import com.yang.study_coroutine.data.LoginRegisterResponseWrapper
import com.yang.study_coroutine.repository.ApiRepository
import kotlinx.coroutines.launch


/**
 * Create by Yang Yang on 2023/5/5
 */
class ApiViewModel : ViewModel() {

    var user = MutableLiveData<LoginRegisterResponseWrapper<LoginRegisterResponse>>()

    fun requestLogin(userName: String, password: String) {

        //默认在Android主线程
        viewModelScope.launch {
            user.value = ApiRepository().requestLogin(userName, password)
        }
    }
}