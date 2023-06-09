package java.com.example.jetpackdemo.ui.activity.paging

import androidx.paging.ItemKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.com.example.jetpackdemo.network.ApiClient


/**
 * Create by Yang Yang on 2023/6/9
 */
class ItemKeyedDataSource : ItemKeyedDataSource<Int, Positional>() {
    override fun getKey(item: Positional): Int {
        return item.id
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Positional>) {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getPositionals3(sincePageId + 1, 10)
            .enqueue(object : Callback<PositionalResponse2> {
                override fun onResponse(
                    call: Call<PositionalResponse2>,
                    response: Response<PositionalResponse2>
                ) {
                    //TODO
                    callback.onResult(response.body()?.data?.datas ?: emptyList())
                }

                override fun onFailure(call: Call<PositionalResponse2>, t: Throwable) {

                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Positional>) {
        
    }

    var sincePageId = 0
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Positional>
    ) {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getPositionals3(sincePageId, 10)
            .enqueue(object : Callback<PositionalResponse2> {
                override fun onResponse(
                    call: Call<PositionalResponse2>,
                    response: Response<PositionalResponse2>
                ) {
                    //TODO
                    callback.onResult(response.body()?.data?.datas ?: emptyList())
                }

                override fun onFailure(call: Call<PositionalResponse2>, t: Throwable) {

                }
            })
    }
}