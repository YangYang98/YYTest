package java.com.example.jetpackdemo.ui.activity.paging

import androidx.paging.DataSource


/**
 * Create by Yang Yang on 2023/6/5
 */
class PagingPositionalDataSourceFactory : DataSource.Factory<Int, Positional>(){
    override fun create(): DataSource<Int, Positional> {
        return PagingPositionalDataSource()
    }
}