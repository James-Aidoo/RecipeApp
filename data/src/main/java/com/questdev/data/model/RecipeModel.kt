package com.questdev.data.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("pk")
    val primaryKey: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("publisher")
    val publisher: String?,

    @SerializedName("rating")
    val rating: Int?,

    @SerializedName("source_url")
    val sourceUrl: String?,

    @SerializedName("featured_image")
    val featuredImage: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("cooking_instructions")
    val cookingInstructions: String?,

    @SerializedName("ingredients")
    val ingredients: List<String>?,

    @SerializedName("date_added")
    val dateAdded: String?,

    @SerializedName("date_updated")
    val dateUpdated: String?
)
