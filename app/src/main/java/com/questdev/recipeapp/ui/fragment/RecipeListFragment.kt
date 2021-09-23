package com.questdev.recipeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.questdev.recipeapp.app.App
import com.questdev.recipeapp.ui.component.*
import com.questdev.recipeapp.ui.state.UiState
import com.questdev.recipeapp.ui.theme.RecipeAppTheme
import com.questdev.recipeapp.viewmodel.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var app: App

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                RecipeAppTheme(darkTheme = app.isDark) {
                    val recipes = viewModel.recipes.value
                    var query by rememberSaveable { mutableStateOf("") }

                    val selectedCategory by remember { viewModel.selectedCategory }
                    val uiState by remember { viewModel.uiState }

                    val scaffoldState = rememberScaffoldState()
                    val scope = rememberCoroutineScope()

                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = { query = it },
                                onExecuteSearch = viewModel::search,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged
                            ) {
                                app.isDark = !app.isDark
                            }
                        },
                        scaffoldState = scaffoldState
                    ) {
                        Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                            when (uiState) {
                                UiState.Loading -> {
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
                                UiState.Result.Success -> {
                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight()
                                    ) {
                                        itemsIndexed(items = recipes) { index, item ->
                                            RecipeCard(recipe = item) {
                                                Log.d(
                                                    TAG,
                                                    "Item with title '${item.title}' at index $index clicked"
                                                )
                                            }
                                        }
                                    }
                                }
                                UiState.Result.Empty -> EmptyListState()
                                UiState.Result.Error -> {
                                    if (viewModel.failure != null) {
                                        scope.launch {
                                            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                viewModel.failure?.value
                                                    ?: "Error occurred"
                                            )
                                            viewModel.clearFailure()
                                        }
                                    }
                                    EmptyListState()
                                }
                            }
                            CircularIndeterminateProgressBar(uiState = uiState)
                        }
                    }
                }
            }
        }
    }

    companion object {
        private val TAG = RecipeListFragment::class.java.simpleName
    }

}
