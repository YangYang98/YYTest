package com.yang.study_coroutine.api

import com.yang.study_coroutine.data.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Create by Yang Yang on 2023/5/10
 */
interface GitHubServiceApi {

    @GET("users/{login}")
    fun getUserAsync(@Path("login") login: String): Deferred<User>

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): User
}