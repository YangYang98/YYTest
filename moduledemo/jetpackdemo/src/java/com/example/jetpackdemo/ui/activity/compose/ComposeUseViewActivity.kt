package java.com.example.jetpackdemo.ui.activity.compose

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetpackdemo.R


/**
 * 不少功能性的传统视图控件在Compose中没有对应的Composable实现，例如SurfaceView、WebView、MapView等。
 * 因此在Compose中可能会有使用传统View控件的需求。Compose提供了名为AndroidView的Composable组件，
 * 允许在Composable中插入任意基于继承自View的传统视图控件。
 *
 * Create by Yang Yang on 2023/5/25
 */
class ComposeUseViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_use_view)

        findViewById<ComposeView>(R.id.compose_root).setContent {
            AndroidView(factory = {context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    loadUrl("https://jetpackcompose.cn/")
                }
            }, modifier = Modifier.fillMaxSize())
        }
    }
}