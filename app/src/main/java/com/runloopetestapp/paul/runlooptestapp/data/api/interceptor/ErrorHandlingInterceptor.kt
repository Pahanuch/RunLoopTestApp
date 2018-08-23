package com.runloopetestapp.paul.runlooptestapp.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class ErrorHandlingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val contentType = response.body()?.contentType()
        val responseString = response.body()?.string() ?: ""

        // TODO add errorResponse, ServerException

        return response.newBuilder().body(ResponseBody.create(contentType, responseString)).build()
    }
}