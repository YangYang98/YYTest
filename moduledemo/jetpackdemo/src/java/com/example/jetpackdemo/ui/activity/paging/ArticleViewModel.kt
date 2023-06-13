package java.com.example.jetpackdemo.ui.activity.paging

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.com.example.jetpackdemo.data.Article
import java.com.example.jetpackdemo.database.ArticleDataBase


/**
 * Create by Yang Yang on 2023/6/12
 */
class ArticleViewModel constructor(private val application: Application): AndroidViewModel(application) {

    lateinit var articlePagedList: LiveData<PagedList<Article>>

    init {
        val config = PagedList.Config.Builder()
            //设置控件占位
            .setEnablePlaceholders(false)
            .setPageSize(PER_PAGE)
            //设置当距离底部还有多少条数据时加载下一页
            .setPrefetchDistance(2)
            //设置首次加载的数量
            .setInitialLoadSizeHint(PER_PAGE * 2)
            .setMaxSize(65536 * PER_PAGE)
            .build()

        val articleDao = ArticleDataBase.getInstance(application).getArticleDao()
        articlePagedList = LivePagedListBuilder (articleDao.getArticleList(), config)
            .setBoundaryCallback(ArticleBoundaryCallback(application = application))
            .build()
    }

    fun refresh() {
        AsyncTask.execute {
            ArticleDataBase.getInstance(application).getArticleDao().clear()
        }
    }
}