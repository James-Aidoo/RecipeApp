package com.questdev.recipeapp.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.questdev.domain.model.Recipe
import com.questdev.recipeapp.events.RecipeListEvent
import com.questdev.recipeapp.ui.state.UiState

@Composable
fun RecipeList(
    uiState: UiState,
    recipes: List<Recipe>,
    scaffoldState: ScaffoldState,
    failure: MutableState<String?>,
    onTriggerEvent: (RecipeListEvent) -> Unit,
    onRecipeClick: (Int) -> Unit
) {
    val tag = "RecipeList"

    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
        when (uiState) {
            UiState.Loading.Initial -> {
                LazyColumn {
                    items(5) {
                        ShimmerRecipeCardItem(
                            colors = listOf(
                                Color.LightGray.copy(alpha = 0.9f),
                                Color.LightGray.copy(alpha = 0.2f),
                                Color.LightGray.copy(alpha = 0.9f)
                            ),
                            height = 250.dp
                        )
                    }
                }
            }
            UiState.Loading.More,
            UiState.Result.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    itemsIndexed(items = recipes) { index, item ->

                        if (index == recipes.lastIndex) {
                            onTriggerEvent(RecipeListEvent.NextPageEvent)
                        }

                        RecipeCard(recipe = item) {
                            onRecipeClick(item.id)
                            Log.d(
                                tag,
                                "Item with title '${item.title}' at index $index clicked"
                            )
                        }
                    }
                }
            }
            UiState.Result.Empty -> EmptyListState()
            UiState.Result.Error -> {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        failure.value ?: "Error occurred"
                    )
                }
                if (recipes.isEmpty()) EmptyListState()
            }
        }
    }

}
