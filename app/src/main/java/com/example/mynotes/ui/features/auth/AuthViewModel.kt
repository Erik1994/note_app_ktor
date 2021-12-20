package com.example.mynotes.ui.features.auth

import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.RegisterUseCase
import com.example.mynotes.ui.common.BaseViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class AuthViewModel(
    private val appDispatchers: AppDispatchers,
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel() {
    private val _registerSharedFlow = MutableSharedFlow<Resource<SimpleData>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val registerSharedFlow = _registerSharedFlow.asSharedFlow()


    fun register(
        email: String,
        password: String,
        repeatedPassword: String
    ) = viewModelScope.launch(appDispatchers.ioDispatcher) {
        val accountRequest = AccountRequest(
            email,
            password,
            repeatedPassword
        )
        _registerSharedFlow.emitAll(registerUseCase(accountRequest))
    }

}