package java.com.example.jetpackdemo.ui.activity.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityPagingBaseBinding


/**
 * Create by Yang Yang on 2023/5/29
 */
class PagingBaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPagingBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging_base)


    }
}