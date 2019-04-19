package com.veeyikpong.fastnewsactivity.api

import com.veeyikpong.fastnewsactivity.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException


object ApiClient {
    private var mClient: OkHttpClient? = null

    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {

                val httpBuilder = OkHttpClient.Builder()

                if(BuildConfig.DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    httpBuilder.addInterceptor(httpLoggingInterceptor)
                }

                mClient = httpBuilder.build()

            }
            return mClient!!
        }
}