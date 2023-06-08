package java.com.example.jetpackdemo.ui.activity.paging

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import java.com.example.jetpackdemo.network.ApiClient
import retrofit2.Callback
import retrofit2.Response


/**
 * Create by Yang Yang on 2023/6/8
 */
class PageKeyedDataSource : PageKeyedDataSource<Int, Positional>() {
    val pageCount = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Positional>
    ) {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getPositionals2(pageCount, 10)
            .enqueue(object : Callback<PositionalResponse2> {
                override fun onResponse(
                    call: Call<PositionalResponse2>,
                    response: Response<PositionalResponse2>
                ) {
                    //TODO
                    callback.onResult(response.body()?.data?.datas ?: emptyList(), null, pageCount + 1)
                }

                override fun onFailure(call: Call<PositionalResponse2>, t: Throwable) {

                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Positional>) {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getPositionals2(params.key, 10)
            .enqueue(object : Callback<PositionalResponse2> {
                override fun onResponse(
                    call: Call<PositionalResponse2>,
                    response: Response<PositionalResponse2>
                ) {
                    //TODO
                    val nextKey = if (response.body()?.data?.hasMore == true) params.key + 1 else null
                    callback.onResult(response.body()?.data?.datas ?: emptyList(), nextKey)
                }

                override fun onFailure(call: Call<PositionalResponse2>, t: Throwable) {

                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Positional>) {

    }
}