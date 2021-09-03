package com.questdev.domain.util

import com.questdev.domain.enums.FoodCategory

val foodCategories = listOf(
    FoodCategory.CHICKEN,
    FoodCategory.BEEF,
    FoodCategory.SOUP,
    FoodCategory.DESSERT,
    FoodCategory.VEGETARIAN,
    FoodCategory.MILK,
    FoodCategory.VEGAN,
    FoodCategory.PIZZA,
    FoodCategory.DONUT
)

fun getFoodCategory(value: String): FoodCategory? {
    val categoryMap = FoodCategory.values().associateBy { it.value }
    return categoryMap[value]
}
