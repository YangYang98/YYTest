package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


/**
 * Create by Yang Yang on 2023/6/8
 */
class StateManagerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CounterScreen()
        }
    }
}

@Composable
fun CounterScreen() {

    /**
     * TODO 状态上提最佳实践
     *
     * 当为了方便Composable共享而上提状态时，应该将状态上提到这几个Composable的最小共同父Composable。
     * 实现共享的同时，避免Scope无意义的扩大。
     */

    var counter by remember {
        mutableStateOf(0)
    }

    CounterComponent(
        counter = counter, { counter++ }
    ) {
        if (counter > 0) {
            counter --
        }
    }
}


@Composable
fun CounterComponent(
    counter: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(text = "Click buttons to adjust your value:")

        Text(
            text = "$counter",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )

        Row {
            Button(
                onClick = onDecrement,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "-")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = onIncrement,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "+")
            }
        }
    }
}