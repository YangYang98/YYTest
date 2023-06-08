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
            CounterComponent()
        }
    }
}

@Composable
fun CounterComponent() {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        var counter by remember {
            mutableStateOf(0)
        }

        Text(text = "Click buttons to adjust your value:")

        Text(
            text = "$counter",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )

        Row {
            Button(
                onClick = { counter-- },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "-")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { counter++ },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "+")
            }
        }
    }
}