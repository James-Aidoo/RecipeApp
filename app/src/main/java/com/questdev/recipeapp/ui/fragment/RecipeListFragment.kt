package com.questdev.recipeapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.questdev.recipeapp.R
import com.questdev.recipeapp.events.RecipeListEvent
import com.questdev.recipeapp.ui.component.CircularIndeterminateProgressBar
import com.questdev.recipeapp.ui.component.RecipeList
import com.questdev.recipeapp.ui.component.SearchAppBar
import com.questdev.recipeapp.ui.theme.RecipeAppTheme
import com.questdev.recipeapp.viewmodel.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.fetchSavedAppTheme()
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                RecipeAppTheme(darkTheme = viewModel.isDark) {
                    val recipes = viewModel.recipes.value
                    val query by remember { viewModel.query }

                    val selectedCategory by remember { viewModel.selectedCategory }
                    val uiState = viewModel.uiState
                    val isBusy = viewModel.isBusy

                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = { viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent) },
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged
                            ) {
                                viewModel.persistAppTheme(!viewModel.isDark)
                            }
                        },
                        scaffoldState = scaffoldState
                    ) {

                        RecipeList(
                            uiState = uiState.value,
                            recipes = recipes,
                            scaffoldState = scaffoldState,
                            failure = viewModel.failure,
                            onTriggerEvent = viewModel::onTriggerEvent
                        ) {
                            val bundle = RecipeFragment.getRequiredArguments(it)
                            findNavController().navigate(R.id.viewRecipe, bundle)
                        }

                        CircularIndeterminateProgressBar(visible = isBusy.value)
                    }
                }
            }
        }
    }

}
