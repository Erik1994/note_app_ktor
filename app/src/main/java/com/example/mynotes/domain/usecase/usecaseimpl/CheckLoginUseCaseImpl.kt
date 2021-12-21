package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.repository.AuthDataRepository
import com.example.mynotes.domain.usecase.CheckLoginUseCase

class CheckLoginUseCaseImpl(private val authDataRepository: AuthDataRepository): CheckLoginUseCase {
    override fun invoke(): Boolean = authDataRepository.isLoggedIn()
}