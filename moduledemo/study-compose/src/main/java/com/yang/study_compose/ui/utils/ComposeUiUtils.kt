package com.yang.study_compose.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


/**
 * Create by Yang Yang on 2023/5/30
 */

@Composable
fun WidthSpacer(
    value: Dp
) = Spacer(modifier = Modifier.width(value))

@Composable
fun HeightSpacer(
    value: Dp
) = Spacer(modifier = Modifier.height(value))