package com.yang.study_coroutine.api

import com.yang.study_coroutine.data.LoginRegisterResponse
import com.yang.study_coroutine.data.LoginRegisterResponseWrapper
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Create by Yang Yang on 2023/5/4
 */
interface WanAndroidApi {

    @POST("/user/login")
    @FormUrlEncoded
    fun login (
        @Field("username") userName: String, @Field("password") password: String
    ) : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>

    @POST("/user/register")
    @FormUrlEncoded
    fun register (
        @Field("username") userName: String, @Field("password") password: String,
        @Field("repassword") repassword: String
    ) : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>
}