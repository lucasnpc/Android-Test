package com.example.androidtest

import android.app.Application
import com.example.androidtest.di.loginModule
import com.example.androidtest.di.newsInfoModule
import com.example.androidtest.di.newsListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)

            modules(loginModule, newsListModule, newsInfoModule)
        }
    }
}