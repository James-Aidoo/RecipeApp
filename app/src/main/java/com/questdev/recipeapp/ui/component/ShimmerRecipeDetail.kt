package com.questdev.recipeapp.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRecipeDetail(
    colors: List<Color>,
    height: Dp
) {

    BoxWithConstraints {

        val transition = rememberInfiniteTransition()
        val padding = 16.dp

        val cardWidth: Float
        val cardHeight: Float
        val gradientWidth: Float

        with(LocalDensity.current) {
            cardWidth = (maxWidth - padding).toPx()
            cardHeight = (maxWidth - padding).toPx()

            gradientWidth = .2f * cardHeight
        }

        val newPosition by transition.animateValue(
            initialValue = Offset(0f, 0f),
            targetValue = Offset(cardWidth, cardHeight),
            typeConverter = Offset.VectorConverter,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = 1300,
                    easing = LinearEasing
                )
            )
        )

        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset(x = newPosition.x - gradientWidth, y = newPosition.y - gradientWidth),
            end = newPosition
        )

        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding),
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height)
                            .background(brush = brush, shape = MaterialTheme.shapes.small)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(height / 10)
                            .fillMaxWidth()
                            .background(brush = brush, shape = MaterialTheme.shapes.small)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(height / 10)
                            .fillMaxWidth(.8f)
                            .background(brush = brush, shape = MaterialTheme.shapes.small)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(height / 10)
                            .fillMaxWidth(.8f)
                            .background(brush = brush, shape = MaterialTheme.shapes.small)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(height / 10)
                            .fillMaxWidth(.6f)
                            .background(brush = brush, shape = MaterialTheme.shapes.small)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(height / 10)
                            .fillMaxWidth(.4f)
                            .background(brush = brush, shape = MaterialTheme.shapes.small)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewShimmerRecipeDetail() {
    ShimmerRecipeDetail(
        colors = listOf(Color.LightGray, Color.DarkGray, Color.Gray),
        height = 260.dp
    )
}
