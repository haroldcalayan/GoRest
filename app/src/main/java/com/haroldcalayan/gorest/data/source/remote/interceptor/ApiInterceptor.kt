package com.haroldcalayan.gorest.data.source.remote.interceptor

import com.haroldcalayan.gorest.BuildConfig
import com.haroldcalayan.gorest.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestBuilder = request.newBuilder().apply {
            header(Constants.HEADER_NAME_ACCEPT, Constants.HEADER_VALUE_APPLICATION_JSON)
            header(Constants.HEADER_NAME_CONTENT_TYPE, Constants.HEADER_VALUE_APPLICATION_JSON)
        }
        if (arrayOf(Constants.METHOD_PUT, Constants.METHOD_POST, Constants.METHOD_PATCH, Constants.METHOD_DELETE).contains(request.method)) {
            requestBuilder.header(
                Constants.HEADER_NAME_AUTHORIZATION,
                "${Constants.HEADER_VALUE_BEARER} ${BuildConfig.GOREST_API_TOKEN}"
            )
        }
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}
