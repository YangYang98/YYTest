package java.com.example.jetpackdemo.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Create by Yang Yang on 2023/6/5
 */
class ApiClient {

    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance = Holder.INSTANCE
    }

    fun <T> instanceRetrofit(apiInterface: Class<T>) : T {
        val okHttpClient = OkHttpClient().newBuilder().myApply {
            readTimeout(10000, TimeUnit.SECONDS)
            connectTimeout(10000, TimeUnit.SECONDS)
            writeTimeout(10000, TimeUnit.SECONDS)
        }.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(apiInterface)
    }
}

fun <T> T.myApply(nm: T.() -> Unit) : T {

    nm()
    return this
}