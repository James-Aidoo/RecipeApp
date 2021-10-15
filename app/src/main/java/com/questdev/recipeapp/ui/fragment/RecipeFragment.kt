package com.questdev.recipeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.questdev.recipeapp.events.RecipeEvent
import com.questdev.recipeapp.ui.component.CircularIndeterminateProgressBar
import com.questdev.recipeapp.ui.component.RecipeDetail
import com.questdev.recipeapp.ui.state.UiState
import com.questdev.recipeapp.ui.theme.RecipeAppTheme
import com.questdev.recipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    private var recipeId: Int = 0

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractArguments()
        viewModel.onTriggerEvent(RecipeEvent.GetRecipe(recipeId))
    }

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
                val recipe = viewModel.recipe.value
                val uiState = viewModel.uiState.value

                RecipeAppTheme(darkTheme = viewModel.isDark) {

                    val scaffoldState = rememberScaffoldState()

                    Scaffold(scaffoldState = scaffoldState) {
                        Box {
                            when (uiState) {
                                is UiState.Loading -> {
                                    Text(
                                        text = "Loading Recipe...",
                                        style = MaterialTheme.typography.h5,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                                is UiState.Result -> {
                                    recipe?.let { RecipeDetail(recipe = it) }
                                }
                            }
                            CircularIndeterminateProgressBar(visible = uiState == UiState.Loading)
                        }
                    }
                }

            }
        }
    }

    private fun extractArguments() {
        arguments?.let {
            recipeId = it.getInt(KEY_RECIPE_ID)
        } ?: return
        Log.d(TAG, "The ID of the clicked recipe: $recipeId")
    }

    companion object {
        private val TAG = RecipeFragment::class.java.simpleName
        private const val KEY_RECIPE_ID = "recipeId"

        fun getRequiredArguments(recipeId: Int): Bundle {
            return Bundle().apply {
                putInt(KEY_RECIPE_ID, recipeId)
            }
        }
    }

}
