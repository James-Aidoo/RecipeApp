package com.questdev.recipeapp.ui.state

sealed class UiState {
    sealed class Loading : UiState() {
        object Initial: Loading()
        object More: Loading()
    }
    sealed class Result : UiState() {
        object Success : Result()
        object Empty : Result()
        object Error : Result()
    }
}
