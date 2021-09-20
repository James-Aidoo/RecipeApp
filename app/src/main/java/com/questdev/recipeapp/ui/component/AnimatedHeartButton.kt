package com.questdev.recipeapp.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedHeartButton() {
    var isRed by rememberSaveable { mutableStateOf(false) }
    val idleIconSize = 50.dp
    val activeIconSize = 80.dp
    var targetSize by remember { mutableStateOf(idleIconSize) }

    val heartSize by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(durationMillis = 200),
        finishedListener = {
            if (targetSize >= activeIconSize)
                targetSize = idleIconSize
        }
    )

    val color by animateColorAsState(targetValue = if (isRed) Color.Red else Color.LightGray)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier
                .height(heartSize)
                .width(heartSize)
                .align(alignment = Alignment.CenterVertically)
                .toggleable(value = isRed, onValueChange = {
                    isRed = it
                    targetSize = activeIconSize
                }),
            tint = color
        )
    }
}

@Preview
@Composable
fun PreviewAnimatedHeartButton() {
    AnimatedHeartButton()
}
