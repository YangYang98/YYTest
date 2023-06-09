package java.com.example.jetpackdemo.ui.activity.paging

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Create by Yang Yang on 2023/6/5
 */
interface PagingModel {

    @GET("/wenda/list/{pageId}/json")
    fun getPositionals(
        @Path("pageId") pageId:Int, @Query("count") count: Int
    ): Call<PositionalResponse>

    @GET("/wenda/list/{pageId}/json")
    fun getPositionals2(
        @Path("pageId") pageId:Int, @Query("count") count: Int
    ): Call<PositionalResponse2>

    @GET("/wenda/list/{pageId}/json")
    fun getPositionals3(
        @Path("pageId") pageId:Int, @Query("count") count: Int
    ): Call<PositionalResponse2>
}