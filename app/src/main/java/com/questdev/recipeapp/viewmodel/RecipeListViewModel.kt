package com.questdev.recipeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.questdev.domain.model.Recipe
import com.questdev.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    val recipes = mutableStateOf<List<Recipe>>(listOf())

    init {
        search("chicken")
    }

    fun search(query: String) {
        viewModelScope.launch {
            val results = repository.searchRecipe(1, query)
            recipes.value = results.orEmpty()
        }
    }

}