package com.yang.study_compose.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.yang.study_compose.R
import com.yang.study_compose.ui.theme.JetPackTheme
import kotlinx.coroutines.launch


/**
 * Create by Yang Yang on 2023/6/7
 */
class ScaffoldActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackTheme {
                ScaffoldView()
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Preview(showBackground = true)
    @Composable
    fun ScaffoldView() {

        var selectedItem by remember {
            mutableStateOf(0)
        }
        val items = listOf (
            Item("主页", R.drawable.ic_capital_visible_dark),
            Item("列表", R.drawable.ic_capital_hide_dark),
            Item("设置", R.drawable.ic_capital_visible_dark)
        )

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Scaffold脚手架")},
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Menu, null)
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigation {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                            icon = { Icon(painter = painterResource(id = item.icon), contentDescription = null) },
                            alwaysShowLabel = true,
                            label = { Text(text = item.name) },
                            selectedContentColor = Color.Cyan,
                            unselectedContentColor = Color.Gray
                        )

                    }
                }
            },
            drawerContent = {
                Text(text = "侧边栏")
            },
            scaffoldState = scaffoldState
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "主页面")
            }
        }

        //通过rememberScaffoldState()获取包含侧边栏状态的ScaffoldState，
        // 当侧边栏被打开时，scaffoldState.drawerState.isOpen被更新为true，
        // 此时，BackHandler开始监听系统返回键事件，返回键被按下则会通过scaffoldState来关闭侧边栏。
        // 这里还通过rememberCoroutineScope()创建了一个协程作用域，因为close()是一个挂起函数。
        BackHandler(
            enabled = scaffoldState.drawerState.isOpen
        ) {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        }
    }
}

data class Item (
    val name: String, val icon: Int
)