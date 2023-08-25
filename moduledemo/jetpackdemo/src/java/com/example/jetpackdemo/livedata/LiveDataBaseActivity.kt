package java.com.example.jetpackdemo.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityLivedataBaseBinding
import java.com.example.jetpackdemo.utils.RedPointManager


/**
 * Create by Yang Yang on 2023/8/25
 */
class LiveDataBaseActivity: AppCompatActivity() {

    private lateinit var mBinding: ActivityLivedataBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_livedata_base)

        mBinding.btnRedManager.setOnClickListener {
            RedPointManager.instance.liveDataA.observe(this) {
                Log.e(RedPointManager.TAG, "liveDataA:$it")
            }
            RedPointManager.instance.liveDataB1.observe(this) {
                Log.e(RedPointManager.TAG, "liveDataB1:$it")
            }
            RedPointManager.instance.liveDataB2.observe(this) {
                Log.e(RedPointManager.TAG, "liveDataB2:$it")
            }
            RedPointManager.instance.liveDataC1.observe(this) {
                Log.e(RedPointManager.TAG, "liveDataC1:$it")
            }
            RedPointManager.instance.liveDataC2.observe(this) {
                Log.e(RedPointManager.TAG, "liveDataC2:$it")
            }
            RedPointManager.instance.testChangeDataC1(true)
        }
    }
}