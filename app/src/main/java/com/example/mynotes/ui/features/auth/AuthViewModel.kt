package com.example.mynotes.ui.features.auth

import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.CheckLoginUseCase
import com.example.mynotes.domain.usecase.LoginUseCase
import com.example.mynotes.domain.usecase.RegisterUseCase
import com.example.mynotes.ui.common.BaseViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val appDispatchers: AppDispatchers,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val checkLoginUseCase: CheckLoginUseCase
) : BaseViewModel() {
    private val _registerSharedFlow = MutableSharedFlow<Resource<SimpleData>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val _loginStateFlow = MutableSharedFlow<Resource<SimpleData>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val registerSharedFlow = _registerSharedFlow.asSharedFlow()
    val loginSharedFlow = _loginStateFlow.asSharedFlow()


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

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch(appDispatchers.ioDispatcher) {
        val accountRequest = AccountRequest(email, password)
        _loginStateFlow.emitAll(loginUseCase(accountRequest))
    }

    fun isLoggedIn() = checkLoginUseCase()

}