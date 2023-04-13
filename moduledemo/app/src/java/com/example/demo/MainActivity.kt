package java.com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.demo.R
import com.example.demo.databinding.ActivityMainBinding
import java.com.example.demo.model.EventHandleListener
import java.com.example.demo.model.MyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        myViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[MyViewModel::class.java]
        activityMainBinding.eventHandler = EventHandleListener(this)
    }

    fun plusNumber(view: View) {
        //textView.text = (++myViewModel.number).toString()
    }
}