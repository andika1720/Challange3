package com.example.challangebinar3

import android.app.Application
import com.example.challangebinar3.di.Module.moduleData
import com.example.challangebinar3.di.Module.uiModule

//import com.example.challangebinar3.di.Module.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    moduleData,
                    uiModule
                )
            )
        }
    }
}