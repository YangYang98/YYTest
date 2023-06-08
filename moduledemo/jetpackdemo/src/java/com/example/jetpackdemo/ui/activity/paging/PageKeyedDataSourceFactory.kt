package java.com.example.jetpackdemo.ui.activity.paging

import androidx.paging.DataSource


/**
 * Create by Yang Yang on 2023/6/8
 */
class PageKeyedDataSourceFactory : DataSource.Factory<Int, Positional>(){
    override fun create(): DataSource<Int, Positional> {
        return PageKeyedDataSource()
    }
}