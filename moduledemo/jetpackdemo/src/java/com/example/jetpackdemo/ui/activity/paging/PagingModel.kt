package java.com.example.jetpackdemo.ui.activity.paging

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Create by Yang Yang on 2023/6/5
 */
interface PagingModel {

    @GET("/project/list/1/json?cid=294")
    fun getPositionals(
        @Query("start") since: Int, @Query("count") count: Int
    ): Call<Positionals>
}