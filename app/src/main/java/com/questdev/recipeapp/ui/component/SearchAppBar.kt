package com.questdev.recipeapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.questdev.domain.util.foodCategories
import com.questdev.recipeapp.R
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(query: String, onQueryChange: (String) -> Unit, onExecuteSearch: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var scrollPosition by rememberSaveable { mutableStateOf(0) }
    var selectedCategory by rememberSaveable { mutableStateOf("") }

    Surface(
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = onQueryChange,
                    label = {
                        Text(
                            text = stringResource(R.string.search),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            stringResource(R.string.search_icon)
                        )
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch(query)
                            focusManager.clearFocus(true)
                        }
                    )
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                state = scrollState
            ) {
                scope.launch { scrollState.animateScrollToItem(scrollPosition) }
                itemsIndexed(foodCategories) { index, category ->
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory == category.value
                    ) {
                        onQueryChange(category.value)
                        scrollPosition = index
                        selectedCategory = category.value
                        onExecuteSearch(category.value)
                    }
                }
            }
        }
    }
}