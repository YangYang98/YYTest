package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.ui.theme.CustomerAssets
import com.yang.study_compose.ui.theme.CustomerColorTheme
import com.yang.study_compose.ui.theme.LocalCustomerAssets
import com.yang.study_compose.ui.theme.customerAssets


/**
 * Create by Yang Yang on 2023/6/8
 */
class ThemeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column {

                SampleText()

                StudyCompositionLocal()

                UseCustomerAssets()
            }


        }
    }
}

@Composable
fun SampleText() {
    Text(
        text = "Hello World",
        color = MaterialTheme.colors.primary
    )
}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    CustomerColorTheme(isDark = true) {
        SampleText()
    }
}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    CustomerColorTheme(isDark = false) {
        SampleText()
    }
}

var LocalString = staticCompositionLocalOf { "Jetpack Compose" }

@Preview(showBackground = true)
@Composable
fun StudyCompositionLocal() {
    CustomerColorTheme(isDark = true) {
        Column {
            CompositionLocalProvider(
                LocalString provides "一级 CompositionLocal"
            ) {
                Text(
                    text = LocalString.current,
                    color = Color.Green
                )

                CompositionLocalProvider(
                    LocalString provides "二级 CompositionLocal"
                ) {
                    Text(
                        text = LocalString.current,
                        color = Color.Blue
                    )
                }

                Text(
                    text = LocalString.current,
                    color = Color.Red
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UseCustomerAssets() {

    Column {

        CompositionLocalProvider(
            LocalCustomerAssets provides CustomerAssets.LightCustomerAssets
        ) {
            Box {
                Image(
                    painter = painterResource(id = MaterialTheme.customerAssets.background),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
                Image(
                    painter = painterResource(id = MaterialTheme.customerAssets.illos),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Image(
                    painter = painterResource(id = MaterialTheme.customerAssets.logo),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        CompositionLocalProvider(
            LocalCustomerAssets provides CustomerAssets.DarkCustomerAssets
        ) {
            Box {
                Image(
                    painter = painterResource(id = MaterialTheme.customerAssets.background),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
                Image(
                    painter = painterResource(id = MaterialTheme.customerAssets.illos),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Image(
                    painter = painterResource(id = MaterialTheme.customerAssets.logo),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

    }
}