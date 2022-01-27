package com.example.mynotes.ui.common

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.mynotes.ui.navigation.NavigationCommand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    private val _navigationFlow = MutableStateFlow<NavigationCommand?>(null)
    val navigationFlow = _navigationFlow.asStateFlow()

    fun navigate(direction: NavDirections) {
        _navigationFlow.value = NavigationCommand.To(direction)
    }

    fun navigateBack() {
        _navigationFlow.value = NavigationCommand.Back
    }

    fun setNavigationNull() {
        _navigationFlow.value = null
    }

}