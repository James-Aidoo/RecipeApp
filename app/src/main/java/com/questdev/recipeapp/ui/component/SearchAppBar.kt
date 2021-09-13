package com.questdev.recipeapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.questdev.domain.enums.FoodCategory
import com.questdev.domain.util.foodCategories
import com.questdev.recipeapp.R
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: (String) -> Unit,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (FoodCategory?) -> Unit,
    onSwitchTheme: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var scrollPosition by rememberSaveable { mutableStateOf(0) }

    Surface(
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface
    ) {
        Column {
            ConstraintLayout(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                val (searchId, toggleThemeId) = createRefs()

                TextField(
                    modifier = Modifier
                        .constrainAs(searchId) {
                            linkTo(start = parent.start, end = toggleThemeId.start, top = parent.top, bottom = parent.bottom)
                            width = Dimension.fillToConstraints
                        },
                    value = query,
                    onValueChange = onQueryChanged,
                    label = {
                        Text(
                            text = stringResource(R.string.search),
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            stringResource(R.string.search_icon),
                            tint = MaterialTheme.colors.onSurface
                        )
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                    ),
                    singleLine = true,
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
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(id = R.string.switch_theme),
                    modifier = Modifier.constrainAs(toggleThemeId) {
                        linkTo(start = searchId.end, end = parent.end, top = parent.top, bottom = parent.bottom, startMargin = 8.dp)
                        width = Dimension.wrapContent
                    }.clickable(onClick = onSwitchTheme)
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
                        isSelected = selectedCategory == category
                    ) {
                        onQueryChanged(category.value)
                        scrollPosition = index
                        onSelectedCategoryChanged(category)
                        onExecuteSearch(category.value)
                    }
                }
            }
        }
    }
}