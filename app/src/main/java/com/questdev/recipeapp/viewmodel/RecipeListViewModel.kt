package com.questdev.recipeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.questdev.domain.enums.FoodCategory
import com.questdev.domain.model.Recipe
import com.questdev.domain.repository.RecipeRepository
import com.questdev.domain.util.foodCategories
import com.questdev.domain.util.getFoodCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    val isBusy = mutableStateOf(false)
    val recipes = mutableStateOf<List<Recipe>>(listOf())
    val selectedCategory = mutableStateOf<FoodCategory?>(null)

    init {
        search("chicken")
    }

    fun search(query: String) {
        viewModelScope.launch {
            isBusy.value = true

            resetRecipeList(query)

            val results = repository.searchRecipe(1, query)
            recipes.value = results.orEmpty()

            isBusy.value = false
        }
    }

    private fun resetRecipeList(query: String) {
        if (!foodCategories.contains(getFoodCategory(query))) {
            selectedCategory.value = null
        }
        recipes.value = listOf()
    }

    fun onSelectedCategoryChanged(category: FoodCategory?) {
        selectedCategory.value = category
    }

}