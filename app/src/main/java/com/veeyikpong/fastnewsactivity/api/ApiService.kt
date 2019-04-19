package com.veeyikpong.fastnewsactivity.api

import com.veeyikpong.fastnewsactivity.BuildConfig
import com.veeyikpong.fastnewsactivity.api.response.SearchNewsResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService{

    @GET("everything")
    fun searchNews(@Query("q") query: String, @Query("apiKey") apiKey: String): Observable<SearchNewsResponse>

    @GET("posts/{postID}")
    fun getPost(@Path("postID") postID: String): Observable<SearchNewsResponse>

    companion object {
        fun create():ApiService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(ApiClient.client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}