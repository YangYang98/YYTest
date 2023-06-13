package java.com.example.jetpackdemo.ui.activity.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jetpackdemo.R


/**
 * Create by Yang Yang on 2023/6/9
 */
class BoundaryCallbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_boundary_callback)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ArticlePagedListAdapter()
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[ArticleViewModel::class.java]
        viewModel.articlePagedList.observe(this) {
            adapter.submitList(it)
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}