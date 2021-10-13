package com.questdev.recipeapp.events

sealed class RecipeEvent {
    data class GetRecipe(val id: Int): RecipeEvent()
}
