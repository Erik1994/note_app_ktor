package com.example.mynotes.data.remote.network.interceptor

import com.example.mynotes.data.remote.UNAUTHORIZED_CODE
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenExpiredInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder().build()
        val response: Response = chain.proceed(request)
        if (response.code == UNAUTHORIZED_CODE) {
            expireToken(chain)
        }

        return response

    }

    private fun expireToken(chain: Interceptor.Chain) {

    }
}