package com.example.demo.api

import android.util.Log
import okhttp3.OkHttpClient


/**
 * Create by Yang Yang on 2023/4/3
 */
data class User(val name: String, val address: String)

val userServiceApi: UserServiceApi by lazy {
    val retrofit = retrofit2.Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor {
            it.proceed(it.request()).apply {
                Log.d("jason", "request:${code()}")
                //Log.d("jason", "boy:${body()?.string()}")
            }
        }.build())
        .baseUrl("http://192.168.1.4:8080/kctlinstudyserver/")
        .addConverterFactory(retrofit2.converter.moshi.MoshiConverterFactory.create())
        .build()
    retrofit.create (UserServiceApi::class.java)
}


interface UserServiceApi {

}