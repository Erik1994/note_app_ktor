package com.example.mynotes.data.remote.network.interceptor

import android.content.SharedPreferences
import com.example.mynotes.data.extensions.get
import com.example.mynotes.data.extensions.put
import com.example.mynotes.data.local.TOKEN_KEY
import com.example.mynotes.data.remote.UNAUTHORIZED_CODE
import com.example.mynotes.data.repository.util.EventManager
import com.example.mynotes.data.repository.util.Events
import com.example.mynotes.ui.extensions.emptyString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenExpiredInterceptor(
    private val eventManager: EventManager,
    private val coroutineScope: CoroutineScope,
    private val sharedPreferences: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder().build()
        val response: Response = chain.proceed(request)
        if (response.code == UNAUTHORIZED_CODE) {
            sendLogOutEvent(eventManager)
        }
        return response
    }

    private fun sendLogOutEvent(eventManager: EventManager) = coroutineScope.launch {
        val token = sharedPreferences.get(TOKEN_KEY, emptyString())
        if (token.isNotEmpty()) {
            sharedPreferences.put(TOKEN_KEY, emptyString())
            eventManager.sendEvent(Events.TokenExpiredEvent)
        }
    }
}