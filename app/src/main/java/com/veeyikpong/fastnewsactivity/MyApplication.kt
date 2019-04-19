package com.veeyikpong.fastnewsactivity

import android.app.Application
import com.veeyikpong.fastnews.injection.netModule
import org.koin.core.context.startKoin

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(netModule)
        }
    }
}