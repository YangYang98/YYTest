package java.com.example.jetpackdemo.ui.activity.paging

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.paging.PagedList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.com.example.jetpackdemo.data.Article
import java.com.example.jetpackdemo.data.ArticleResponse
import java.com.example.jetpackdemo.database.ArticleDataBase
import java.com.example.jetpackdemo.network.ApiClient


/**
 * Create by Yang Yang on 2023/6/12
 */
class ArticleBoundaryCallback(private val application: Application) : PagedList.BoundaryCallback<Article>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        //加载第一页数据
        getTopData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        super.onItemAtEndLoaded(itemAtEnd)
        //加载下一页数据
        getTopAfterData(itemAtEnd)
    }

    private fun getTopData() {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getArticles(0)
            .enqueue(object : Callback<ArticleResponse> {
                override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                    if (response.body() != null) {
                        insertArticles(response.body()?.data?.datas)
                        //callback.onResult(response.body()?.data?.datas ?: emptyList(), response.body()?.data?.curPage ?: 0, response.body()?.data?.total ?: 0)
                    }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    //callback.onResult(emptyList(), 0)
                    Log.e("YANGYANG", "错误")
                }

            })
    }

    private fun insertArticles(datas: List<Article>?) {
        if (datas?.isEmpty() == true) {
            return
        }
        AsyncTask.execute {
            ArticleDataBase.getInstance(application).getArticleDao().insertArticles(datas!!)
        }
    }

    private fun getTopAfterData(article: Article) {
        ApiClient.instance.instanceRetrofit(PagingModel::class.java)
            .getArticles(0)
            .enqueue(object : Callback<ArticleResponse> {
                override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                    if (response.body() != null) {
                        insertArticles(response.body()?.data?.datas)
                        //callback.onResult(response.body()?.data?.datas ?: emptyList(), response.body()?.data?.curPage ?: 0, response.body()?.data?.total ?: 0)
                    }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    //callback.onResult(emptyList(), 0)
                    Log.e("YANGYANG", "错误")
                }

            })
    }


}