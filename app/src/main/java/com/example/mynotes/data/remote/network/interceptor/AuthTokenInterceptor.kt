package com.example.mynotes.data.remote.network.interceptor

import android.content.SharedPreferences
import com.example.mynotes.data.extensions.get
import com.example.mynotes.data.local.TOKEN_KEY
import com.example.mynotes.data.remote.AUTHORIZATION
import com.example.mynotes.data.remote.BEARER
import com.example.mynotes.data.remote.Constants.IGNORE_AUTH_URLS
import com.example.mynotes.ui.extensions.emptyString
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.get(TOKEN_KEY, emptyString())
        val request = chain.request()
        if (request.url.encodedPath in IGNORE_AUTH_URLS) {
            return chain.proceed(request)
        }
        val authenticatedRequest = request.newBuilder()
            .addHeader(AUTHORIZATION, "$BEARER$token")
            .build()
        return chain.proceed(authenticatedRequest)
    }
}