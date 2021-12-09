package com.example.mynotes.ui.extensions

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

private const val DEBOUNCE_DELAY_MS: Long = 300L

@ExperimentalCoroutinesApi
fun View.clicks(): Flow<Unit> = callbackFlow {
    setOnClickListener { safeOffer(Unit) }
    awaitClose { setOnClickListener(null) }
}

@FlowPreview
@ExperimentalCoroutinesApi
fun View.debounceClicks(): Flow<Unit> = callbackFlow {
    setOnClickListener { safeOffer(Unit) }
    awaitClose { setOnClickListener(null) }
}.debounce(DEBOUNCE_DELAY_MS)

@ExperimentalCoroutinesApi
fun <T> ProducerScope<T>.safeOffer(element: T) {
    if (!isClosedForSend) {
        trySend(element)
    }
}