package java.com.example.jetpackdemo.ui.activity.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R


/**
 * PositionalDataSource
 *
 * 适用于可通过任意位置加载数据且目标数据源数量固定的情况。
 * 例如,请求时携带的参数为start=2&count=5,则表示向服务端请求从第2条数据开始往后的5条数据。
 *
 * Create by Yang Yang on 2023/5/29
 */
class PositionalDataSourceActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_positional_data_source)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PositionalPagedListAdapter()
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[PagingPositionalDataSourceViewModel::class.java]
        viewModel.positionalPagedList.observe(this) {
            adapter.submitList(it)
        }
    }
}