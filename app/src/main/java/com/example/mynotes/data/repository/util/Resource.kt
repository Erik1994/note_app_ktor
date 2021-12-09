package com.example.mynotes.data.repository.util

sealed class Resource<out T> {
    data class Success<out T>(
        val data: T,
        val code: Int? = null
    ) : Resource<T>()

    data class Error<out T>(
        val exception: Exception,
        val data: T?,
        val code: Int? = null
    ) : Resource<T>()

    object Loading : Resource<Nothing>()
}