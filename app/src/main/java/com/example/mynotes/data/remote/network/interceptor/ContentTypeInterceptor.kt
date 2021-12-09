package com.example.mynotes.data.remote.network.interceptor

import com.example.mynotes.data.remote.ACCEPT
import com.example.mynotes.data.remote.CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Response

class ContentTypeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader(ACCEPT, CONTENT_TYPE).build()
        return chain.proceed(request)
    }
}