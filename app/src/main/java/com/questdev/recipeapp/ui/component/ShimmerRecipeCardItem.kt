package com.questdev.recipeapp.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRecipeCardItem(
    colors: List<Color>,
    height: Dp
) {
    val transition = rememberInfiniteTransition()
    val padding = 16.dp

    BoxWithConstraints {

        val cardWidthPx: Float
        val cardHeightPx: Float
        val gradientWidth: Float

        with(LocalDensity.current) {
            cardWidthPx = (maxWidth - padding).toPx()
            cardHeightPx = (maxWidth - padding).toPx()

            gradientWidth = .2f * cardHeightPx
        }

        val newPosition by transition.animateValue(
            initialValue = Offset(0f, 0f),
            targetValue = Offset(cardWidthPx, cardHeightPx),
            typeConverter = Offset.VectorConverter,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = 1300,
                    delayMillis = 300,
                    easing = LinearEasing
                )
            )
        )

        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset(newPosition.x - gradientWidth, newPosition.y - gradientWidth),
            end = newPosition
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .background(brush = brush, shape = MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.height(padding))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 10)
                    .background(brush = brush, shape = MaterialTheme.shapes.small)
            )
        }
    }
}
