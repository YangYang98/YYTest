package com.yang.study_coroutine.repository

import com.yang.study_coroutine.api.ApiClient
import com.yang.study_coroutine.api.WanAndroidApi
import com.yang.study_coroutine.data.LoginRegisterResponse
import com.yang.study_coroutine.data.LoginRegisterResponseWrapper


/**
 * Create by Yang Yang on 2023/5/5
 */
class ApiRepository {

    suspend fun requestLogin(userName: String, password: String): LoginRegisterResponseWrapper<LoginRegisterResponse> {
        return ApiClient.instance.instanceRetrofit(WanAndroidApi::class.java).loginCoroutine(userName, password)
    }
}