package java.com.example.jetpackdemo.ui.activity.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R
import java.com.example.jetpackdemo.ui.effectfactory.SpringEdgeEffect


/**
 * Create by Yang Yang on 2023/6/9
 */
class ItemKeyedDataSourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_item_keyed_data_source)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.edgeEffectFactory = SpringEdgeEffect()
        val adapter = PositionalPagedListAdapter()
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[ItemKeyedDataSourceViewModel::class.java]
        viewModel.pageKeyedPagedList.observe(this) {
            adapter.submitList(it)
        }
    }
}