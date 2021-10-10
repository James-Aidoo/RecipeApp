package com.questdev.recipeapp.ui.state

sealed class UiState {
    object Loading : UiState()
    sealed class Result : UiState() {
        object Success : Result()
        object Empty : Result()
        object Error : Result()
    }
}
