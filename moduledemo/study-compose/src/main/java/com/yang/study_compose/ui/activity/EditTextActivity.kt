package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.R
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/29
 */
class EditTextActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
                    ) {
                        EditText()
                        Spacer(modifier = Modifier.height(20.dp))
                        BasicTextFieldView()

                        Spacer(modifier = Modifier.height(20.dp))
                        BiliBiliSearchBar()
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun EditText() {
        var text by remember {
            mutableStateOf("")
        }
        TextField(value = text, onValueChange = {
            //来自软键盘的输入内容不会直接更新TextField, TextField需要通过观察额外的状态更新自身，这也体现了声明式UI中“状态驱动UI”的基本理念。
            text = it
            //Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
        }, label = { Text(text = "我是标签")})

        var userName by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        Column {
            TextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                label = { Text(text = "前置图标") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.AccountBox, contentDescription = stringResource(
                        id = R.string.app_name
                    ))
                }
            )
            TextField(value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "后置图标") },
                trailingIcon = {
                    Icon(painter = painterResource(id = R.drawable.ic_capital_hide_dark), contentDescription = stringResource(
                        id = R.string.app_name
                    ))
                }
            )
        }

        var outLineTextUserName by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = outLineTextUserName,
            onValueChange = {
            outLineTextUserName = it
            }, label = { Text(text = "边框样式输入框") },
        )

        TextField(value = text, onValueChange = {
            text = it
        }, label = { Text(text = "TextField改变高度")}, modifier = Modifier.height(30.dp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun BasicTextFieldView() {
        var text by remember {
            mutableStateOf("")
        }
        Text(text = "BasicTextField自定义EditText", modifier = Modifier.padding(20.dp, 0.dp))
        //TODO innerTextField是框架定义好给我们使用的东西，它就是文字输入的入口，
        // 所以需要创建好一个完整的输入框界面，并在合适的地方调用这个函数。
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            decorationBox = { innerTextField ->
                Column(Modifier.padding(20.dp, 0.dp)) {
                    innerTextField()
                    Divider(
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Green)
                    )
                }
            }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun BiliBiliSearchBar() {
        var text by remember {
            mutableStateOf("")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFFD3D3D3)),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                decorationBox = { innerTextField ->
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(id = R.string.app_name))
                        Box (
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (text.isEmpty()) {
                                Text(
                                    text = "输入想要搜索的内容",
                                    style = TextStyle(
                                        color = Color(0, 0, 0, 128)
                                    )
                                )
                            }
                            innerTextField()
                        }
                        if (text.isNotEmpty()) {
                            IconButton(onClick = {
                                text = ""
                            }, modifier = Modifier.size(16.dp)
                            ) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(
                                    id = R.string.app_name
                                ))
                            }
                        }
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .background(Color.White, CircleShape)
                    .height(30.dp)
                    .fillMaxWidth()
            )
        }
    }
}