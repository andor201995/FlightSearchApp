package com.andor.flightsearch

import android.app.Application
import com.andor.flightsearch.core.dependencyinjection.appModule
import com.andor.flightsearch.core.dependencyinjection.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FlightSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FlightSearchApplication)
            modules(listOf(
                appModule,
                networkModule
            ))
        }

    }
}