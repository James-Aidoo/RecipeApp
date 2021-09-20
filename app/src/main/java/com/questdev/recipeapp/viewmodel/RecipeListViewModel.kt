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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    val isBusy = mutableStateOf(false)
    val recipes = mutableStateOf<List<Recipe>>(listOf())
    val selectedCategory = mutableStateOf<FoodCategory?>(null)

    private val searchRecipe = SearchRecipe(repository)

    init {
        search("chicken")
    }

    fun search(query: String) {
        isBusy.value = true

        resetRecipeList(query)

        searchRecipe(RecipeQueryParam(1, query), viewModelScope) {
            it.fold(::handleFailure, ::handleRecipeList)
            isBusy.value = false
        }
    }

    private fun handleRecipeList(result: List<Recipe>) {
        recipes.value = result
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetworkConnection -> {  }
            is Failure.ServerError -> { Log.d("handleFailure", failure.throwable?.stackTrace.toString()) }
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

}
