package java.com.example.jetpackdemo.ui.activity.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityRoomBaseBinding
import java.com.example.jetpackdemo.listener.RoomEventHandleListener

class RoomBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRoomBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_room_base)
        binding.eventHandle = RoomEventHandleListener(this)
    }
}