package com.yang.study_compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.R
import com.yang.study_compose.ui.theme.gray
import com.yang.study_compose.ui.theme.h2
import com.yang.study_compose.ui.theme.h3
import com.yang.study_compose.ui.theme.medium
import com.yang.study_compose.ui.theme.pink100
import com.yang.study_compose.ui.theme.pink900
import com.yang.study_compose.ui.theme.white


/**
 * Create by Yang Yang on 2023/6/8
 */
@Composable
fun WelcomePage() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pink100)
    ) {

        Image(
              painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_light_welcome_bg)),
            contentDescription = "welcome_bg",
            modifier = Modifier.fillMaxSize())

        WelcomeContent()
    }
}

@Composable
fun WelcomeContent() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(72.dp))
        LeafImage()
        Spacer(modifier = Modifier.height(48.dp))
        WelcomeTitle()
        Spacer(modifier = Modifier.height(40.dp))
        WelcomeButtons()

    }
}

@Composable
fun LeafImage() {
    Image(
        painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_launcher_background)),
        contentDescription = "welcome_illos",
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 88.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomeTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Bloom",
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            Text(
                text = "Beautiful home garden solutions",
                textAlign = TextAlign.Center,
                style = h3,
                color = gray
                )
        }
    }
}

@Composable
fun WelcomeButtons() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(medium)
        ) {
            Text(
                text = "Create account",
                style = h2,
                color = white
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Log in",
                style = h2,
                color = pink900
                )
        }
    }
}