package com.questdev.domain.model

data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val publisher: String = "",
    val featuredImage: String = "",
    val rating: Int = 0,
    val sourceUrl: String = "",
    val description: String = "",
    val cookingInstructions: String = "",
    val ingredients: List<String> = listOf(),
    val dateAdded: String = "",
    val dateUpdated: String = ""
)