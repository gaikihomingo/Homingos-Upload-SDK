package com.homingos.sdk.network

import com.homingos.sdk.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    companion object {
        private const val AUTH_HEADER_KEY = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if (!request.url().toString().contains("amazonaws", true)) {
            //add auth header to homingos.com api request
            requestBuilder.header(AUTH_HEADER_KEY, BuildConfig.AUTH)
        }
        return chain.proceed(requestBuilder.build())
    }

}