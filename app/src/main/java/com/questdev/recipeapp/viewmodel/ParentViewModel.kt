package com.questdev.recipeapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.questdev.domain.exception.Failure
import com.questdev.domain.interactor.usecase.GetThemeIsDark
import com.questdev.domain.interactor.usecase.SaveAppThemeState
import com.questdev.domain.repository.DataRepository
import com.questdev.recipeapp.ui.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class ParentViewModel : ViewModel() {

    @Inject
    lateinit var dataRepository: DataRepository

    val uiState = mutableStateOf<UiState>(UiState.Loading)
    var failure = mutableStateOf<String?>(null)

    var isDark by mutableStateOf(false)

    fun persistAppTheme(isDark: Boolean) {
        SaveAppThemeState(dataRepository)(isDark, viewModelScope) { it.fold(::handleFailure) {} }
    }

    fun fetchSavedAppTheme() {
        GetThemeIsDark(dataRepository).invoke(Unit, ::applySavedTheme)
    }

    private fun applySavedTheme(themeFlow: Flow<Boolean>) {
        viewModelScope.launch {
            themeFlow
                .catch { handleFailure(Failure.ServerError(it)) }
                .collect {
                    isDark = it
                }
        }
    }

    fun handleFailure(failure: Failure) {
        extraOnErrorAction()

        uiState.value = UiState.Result.Error
        this.failure = mutableStateOf(failure.throwable?.message)

        when (failure) {
            is Failure.NetworkConnection -> {
            }
            is Failure.ServerError -> {
                Log.d("handleFailure", failure.throwable?.stackTraceToString().orEmpty())
            }
            is Failure.FeatureFailure -> {
            }
        }
    }

    abstract fun extraOnErrorAction()

}
