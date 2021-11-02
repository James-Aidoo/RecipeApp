package com.questdev.recipeapp.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.questdev.domain.model.Recipe
import com.questdev.recipeapp.R
import com.questdev.recipeapp.ui.theme.Black400
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun RecipeDetail(recipe: Recipe) {
    val colorFilterMul =
        animateColorAsState(targetValue = if (MaterialTheme.colors.isLight) Color.White else Black400)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        item {
            Card(
                shape = MaterialTheme.shapes.small, elevation = 0.dp, modifier = Modifier.padding(bottom = 8.dp)
            ) {
                GlideImage(
                    imageModel = recipe.featuredImage,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    colorFilter = ColorFilter.lighting(colorFilterMul.value, Color.Unspecified),
                    placeHolder = painterResource(id = R.drawable.empty_plate),
                    error = painterResource(id = R.drawable.empty_plate)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(align = Alignment.Start)
                )
                Text(
                    text = recipe.rating.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h5
                )
            }
            Text(
                text =
                if (recipe.dateUpdated.isNotBlank())
                    "Updated on ${recipe.dateUpdated} by ${recipe.publisher}"
                else
                    "by ${recipe.publisher}",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            for (ingredient in recipe.ingredients) {
                Text(
                    text = ingredient,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }

}
