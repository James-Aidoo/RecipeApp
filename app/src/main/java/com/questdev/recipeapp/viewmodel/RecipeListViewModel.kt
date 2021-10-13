package com.questdev.recipeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.questdev.domain.enums.FoodCategory
import com.questdev.domain.interactor.usecase.SearchRecipe
import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.repository.RecipeRepository
import com.questdev.domain.util.foodCategories
import com.questdev.domain.util.getFoodCategory
import com.questdev.recipeapp.events.RecipeListEvent
import com.questdev.recipeapp.events.RecipeListEvent.NewSearchEvent
import com.questdev.recipeapp.events.RecipeListEvent.NextPageEvent
import com.questdev.recipeapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    repository: RecipeRepository,
) : ParentViewModel() {

    val recipes = mutableStateOf<MutableList<Recipe>>(mutableListOf())

    val query = mutableStateOf("")
    val selectedCategory = mutableStateOf<FoodCategory?>(null)
    private var page = 1
    private var pageBeingFetched = page

    private var loadingMore = false
    var isBusy = mutableStateOf(false)

    private val searchRecipe = SearchRecipe(repository)

    init {
        onTriggerEvent(NewSearchEvent)
    }

    fun onTriggerEvent(event: RecipeListEvent) {
        isBusy.value = true
        when (event) {
            NewSearchEvent -> newSearch()
            NextPageEvent -> loadMore()
        }
    }

    private fun newSearch() {
        uiState.value = UiState.Loading

        resetRecipeList()
        search()
    }

    private fun loadMore() {
        if (recipes.value.size >= page * DEFAULT_PAGE_SIZE && !loadingMore) {
            pageBeingFetched = page + 1
            loadingMore = true
            search()
        }
    }

    private fun search() {
        searchRecipe(RecipeQueryParam(pageBeingFetched, query.value), viewModelScope) {
            it.fold(::handleFailure, ::handleRecipeList)
        }
    }

    private fun handleRecipeList(result: List<Recipe>) {
        loadingMore = false
        isBusy.value = false
        recipes.let {
            if (pageBeingFetched >= 2) {
                it.value.addAll(result)
                page = pageBeingFetched
            } else {
                it.value = result.toMutableList()
            }
        }

        uiState.value = if (result.isNotEmpty()) UiState.Result.Success else UiState.Result.Empty
    }

    override fun extraOnErrorAction() {
        loadingMore = false
        isBusy.value = false
    }

    private fun resetRecipeList() {
        viewModelScope.launch {
            if (!foodCategories.contains(getFoodCategory(query.value))) {
                selectedCategory.value = null
            }
            recipes.value.clear()
            page = 1
        }
    }

    fun onSelectedCategoryChanged(category: FoodCategory?) {
        selectedCategory.value = category
    }

    fun onQueryChanged(newQuery: String) {
        query.value = newQuery
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 30
    }

}
