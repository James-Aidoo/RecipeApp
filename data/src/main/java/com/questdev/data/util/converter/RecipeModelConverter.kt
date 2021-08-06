package com.questdev.data.util.converter

import com.questdev.data.model.RecipeModel
import com.questdev.domain.model.Recipe

fun RecipeModel.toRecipe() = Recipe(
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

fun Recipe.toApiModel() = RecipeModel(
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

fun List<Recipe>.toApiModelList() = this.map { it.toApiModel() }

fun List<RecipeModel>.toRecipeList() = this.map { it.toRecipe() }