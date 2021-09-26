package com.questdev.recipeapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.questdev.domain.enums.FoodCategory
import com.questdev.domain.exception.Failure
import com.questdev.domain.interactor.usecase.SearchRecipe
import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.repository.RecipeRepository
import com.questdev.domain.util.foodCategories
import com.questdev.domain.util.getFoodCategory
import com.questdev.recipeapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    repository: RecipeRepository
) : ViewModel() {

    val uiState = mutableStateOf<UiState>(UiState.Loading)
    val recipes = mutableStateOf<List<Recipe>>(listOf())
    var failure = mutableStateOf<String?>(null)

    val query = mutableStateOf("")
    val selectedCategory = mutableStateOf<FoodCategory?>(null)

    private val searchRecipe = SearchRecipe(repository)

    init {
        search()
    }

    fun search() {
        uiState.value = UiState.Loading

        resetRecipeList(query.value)

        searchRecipe(RecipeQueryParam(1, query.value), viewModelScope) {
            it.fold(::handleFailure, ::handleRecipeList)
        }
    }

    private fun handleRecipeList(result: List<Recipe>) {
        recipes.value = result
        uiState.value = if (result.isNotEmpty()) UiState.Result.Success else UiState.Result.Empty
    }

    private fun handleFailure(failure: Failure) {
        uiState.value = UiState.Result.Error
        this.failure = mutableStateOf(failure.throwable?.message)

        when (failure) {
            is Failure.NetworkConnection -> {  }
            is Failure.ServerError -> {
                Log.d("handleFailure", failure.throwable?.stackTrace.toString())
            }
            is Failure.FeatureFailure -> {  }
        }
    }

    private fun resetRecipeList(query: String) {
        viewModelScope.launch {
            if (!foodCategories.contains(getFoodCategory(query))) {
                selectedCategory.value = null
            }
            recipes.value = listOf()
        }
    }

    fun onSelectedCategoryChanged(category: FoodCategory?) {
        selectedCategory.value = category
    }

    fun onQueryChanged(newQuery: String) {
        query.value = newQuery
    }

}
