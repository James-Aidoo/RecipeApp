package com.questdev.recipeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.questdev.recipeapp.ui.component.RecipeCard
import com.questdev.recipeapp.ui.component.SearchAppBar
import com.questdev.recipeapp.viewmodel.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value
                var query by rememberSaveable { mutableStateOf("") }

                Column {

                    SearchAppBar(
                        query = query,
                        onQueryChange = { query = it },
                        onExecuteSearch = viewModel::search
                    )

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
            }
        }
    }

    companion object {
        private val TAG = RecipeListFragment::class.java.simpleName
    }

}