package com.questdev.recipeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.questdev.domain.interactor.usecase.GetRecipe
import com.questdev.domain.model.Recipe
import com.questdev.domain.repository.RecipeRepository
import com.questdev.recipeapp.events.RecipeEvent
import com.questdev.recipeapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    repository: RecipeRepository
) : ParentViewModel() {

    val recipe = mutableStateOf<Recipe?>(null)

    private val getRecipe = GetRecipe(repository)

    fun onTriggerEvent(event: RecipeEvent) {
        when (event) {
            is RecipeEvent.GetRecipe -> {
                getRecipe(event.id)
            }
        }
    }

    private fun getRecipe(recipeId: Int) {
        uiState.value = UiState.Loading
        getRecipe(recipeId, viewModelScope) { it.fold(::handleFailure, ::handleRecipe) }
    }

    private fun handleRecipe(recipe: Recipe) {
        uiState.value = UiState.Result.Success
        this.recipe.value = recipe
    }

    override fun extraOnErrorAction() {

    }

}
