package java.com.example.jetpackdemo.ui.activity.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import com.example.jetpackdemo.R

class ComposeBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_base)

        findViewById<ComposeView>(R.id.compose_root).setContent {
            Text(text = "Hello YangYang")
        }
    }
}