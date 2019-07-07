package br.com.ledszeppelin.data.interceptor

import br.com.ledszeppelin.data.AUTHORIZATION
import br.com.ledszeppelin.data.AUTHORIZATION_TYPE
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader(AUTHORIZATION, "$AUTHORIZATION_TYPE $token")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}