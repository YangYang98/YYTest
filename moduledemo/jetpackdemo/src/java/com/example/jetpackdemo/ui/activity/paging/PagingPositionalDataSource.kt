package java.com.example.jetpackdemo.ui.activity.paging

import android.util.Log
import androidx.paging.PositionalDataSource
import retrofit2.Call
import java.com.example.jetpackdemo.network.ApiClient
import retrofit2.Callback
import retrofit2.Response


/**
 * Create by Yang Yang on 2023/6/5
 */
const val PER_PAGE = 10

class PagingPositionalDataSource : PositionalDataSource<Positional>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Positional>) {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getPositionals(0, PER_PAGE)
            .enqueue(object : Callback<PositionalResponse> {
                override fun onResponse(call: Call<PositionalResponse>, response: Response<PositionalResponse>) {
                    if (response.body() != null) {
                        callback.onResult(response.body()?.data?.datas ?: emptyList(), response.body()?.data?.curPage ?: 0, response.body()?.data?.total ?: 0)
                    }
                    Log.e("YANGYANG", response.toString())
                }

                override fun onFailure(call: Call<PositionalResponse>, t: Throwable) {
                    callback.onResult(emptyList(), 0)
                    Log.e("YANGYANG", "错误")
                }

            })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Positional>) {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getPositionals(params.startPosition / PER_PAGE, PER_PAGE)
            .enqueue(object : Callback<PositionalResponse> {
                override fun onResponse(call: Call<PositionalResponse>, response: Response<PositionalResponse>) {
                    if (response.body() != null) {
                        callback.onResult(response.body()?.data?.datas ?: emptyList())
                    }
                }

                override fun onFailure(call: Call<PositionalResponse>, t: Throwable) {

                }

            })
    }
}