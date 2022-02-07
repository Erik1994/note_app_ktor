package com.example.mynotes.data.repository.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class EventManager {
    private val _event = Channel<Events?>()
    val event = _event.receiveAsFlow()

    suspend fun sendEvent(event: Events?) = _event.send(event)
}