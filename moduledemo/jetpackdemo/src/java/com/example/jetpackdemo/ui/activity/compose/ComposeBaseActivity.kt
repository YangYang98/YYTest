package java.com.example.jetpackdemo.ui.activity.compose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jetpackdemo.R

class ComposeBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_base)

        findViewById<Button>(R.id.btn_include_compose).setOnClickListener {
            startActivity(Intent(this, ComposeIncludeProjectActivity::class.java))
        }

        findViewById<Button>(R.id.btn_compose_use_view).setOnClickListener {
            startActivity(Intent(this, ComposeUseViewActivity::class.java))
        }
    }
}