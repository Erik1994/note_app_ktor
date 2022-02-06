package com.example.mynotes.data.remote.network.interceptor

import android.content.Context
import android.content.SharedPreferences
import com.example.mynotes.R
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
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder().build()
        val response: Response = chain.proceed(request)
        if (response.code == UNAUTHORIZED_CODE) {
            sharedPreferences.put(TOKEN_KEY, emptyString())
            sendLogOutEvent(eventManager, context.getString(R.string.token_expire_message))
        }
        return response
    }

    private fun sendLogOutEvent(eventManager: EventManager, message: String) = coroutineScope.launch {
        eventManager.sendEvent(Events.LogOutEvent(message))
    }
}