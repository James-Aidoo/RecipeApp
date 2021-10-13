package com.questdev.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int,
    val sourceUrl: String,
    val description: String,
    val cookingInstructions: String,
    val ingredients: List<String>,
    val dateAdded: String,
    val dateUpdated: String
) {
    companion object {
        fun empty() = Recipe(
            id = 0,
            title = "",
            publisher = "",
            featuredImage = "",
            rating = 0,
            sourceUrl = "",
            description = "",
            cookingInstructions = "",
            ingredients = listOf(),
            dateAdded = "",
            dateUpdated = ""
        )
    }
}
