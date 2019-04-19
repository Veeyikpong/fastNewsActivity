package com.veeyikpong.fastnews.injection

import com.veeyikpong.fastnewsactivity.api.ApiService
import org.koin.dsl.module

private val apiServiceProvider = ApiService.create()
val netModule = module {
    single{ apiServiceProvider}
}