package com.questdev.recipeapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndeterminateProgressBar(visible: Boolean) {

    if (visible) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(20.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    }

}
