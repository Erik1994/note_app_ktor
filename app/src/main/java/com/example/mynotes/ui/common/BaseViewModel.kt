package com.example.mynotes.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.mynotes.ui.navigation.NavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    private val _navigationFlow = MutableSharedFlow<NavigationCommand>()
    val navigationFlow = _navigationFlow.asSharedFlow()

    fun navigate(direction: NavDirections) {
        viewModelScope.launch {
            _navigationFlow.emit(NavigationCommand.To(direction))
        }
    }

}