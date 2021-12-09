package com.example.mynotes.ui.navigation

class SingleLiveEvent<out T> (private val content: T) {

    private var hasBeenHandled: Boolean = false

    fun getContentIfNotHandled(): T? = if(hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    fun peekContent(): T = content
}