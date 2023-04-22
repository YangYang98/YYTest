package java.com.example.jetpackdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityRecycleViewBindingBinding
import java.com.example.jetpackdemo.data.User2
import java.com.example.jetpackdemo.ui.adapter.RecyclerViewAdapter

class RecycleViewBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRecycleViewBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycle_view_binding)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerViewAdapter(ArrayList<User2>().apply {
            add(User2("11", "111", "https://img2.baidu.com/it/u=3618236253,1028428296&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682355600&t=70c3f2537cbbc106767e276311e60a24"))
            add(User2("22", "222", "https://img2.baidu.com/it/u=3618236253,1028428296&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682355600&t=70c3f2537cbbc106767e276311e60a24"))
            add(User2("33", "333", "https://img2.baidu.com/it/u=3618236253,1028428296&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682355600&t=70c3f2537cbbc106767e276311e60a24"))
            add(User2("44", "444", "https://img2.baidu.com/it/u=3618236253,1028428296&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1682355600&t=70c3f2537cbbc106767e276311e60a24"))
        })
    }
}