package java.com.example.jetpackdemo.ui.activity.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList


/**
 * Create by Yang Yang on 2023/6/5
 */
class PagingPositionalDataSourceViewModel : ViewModel() {

    lateinit var positionalPagedList: LiveData<PagedList<Positional>>

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

        positionalPagedList = LivePagedListBuilder (PagingPositionalDataSourceFactory(), config).build()
    }
}