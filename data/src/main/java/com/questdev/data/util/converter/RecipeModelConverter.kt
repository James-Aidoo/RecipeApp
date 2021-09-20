package com.questdev.data.util.converter

import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeDto

fun RecipeDto.toRecipe() = Recipe(
    id = this.primaryKey ?: 0,
    title = this.title.orEmpty(),
    publisher = this.title.orEmpty(),
    featuredImage = this.featuredImage.orEmpty(),
    rating = this.rating ?: 0,
    sourceUrl = this.sourceUrl.orEmpty(),
    description = this.description.orEmpty(),
    cookingInstructions = this.cookingInstructions.orEmpty(),
    ingredients = this.ingredients.orEmpty(),
    dateAdded = this.dateAdded.orEmpty(),
    dateUpdated = this.dateUpdated.orEmpty()
)

fun Recipe.toApiModel() = RecipeDto(
    primaryKey = this.id,
    title = this.title,
    publisher = this.title,
    featuredImage = this.featuredImage,
    rating = this.rating,
    sourceUrl = this.sourceUrl,
    description = this.description,
    cookingInstructions = this.cookingInstructions,
    ingredients = this.ingredients,
    dateAdded = this.dateAdded,
    dateUpdated = this.dateUpdated
)

fun List<Recipe>.toRecipeDtoList() = this.map { it.toApiModel() }

fun List<RecipeDto>.toRecipeList() = this.map { it.toRecipe() }
