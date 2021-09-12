package com.questdev.recipeapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.questdev.domain.model.Recipe
import com.questdev.recipeapp.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(10.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column {
            GlideImage(
                imageModel = recipe.featuredImage,
                contentDescription = stringResource(R.string.recipe_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                circularRevealedEnabled = true,
                contentScale = ContentScale.Crop,
                placeHolder = ImageBitmap.imageResource(id = R.drawable.empty_plate),
                error = ImageBitmap.imageResource(id = R.drawable.empty_plate),
            )
            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = recipe.rating.toString(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }

}

@Composable
@Preview
fun PreviewRecipeCard() {
    RecipeCard(recipe = Recipe(title = "Title", rating = 39)) {}
}