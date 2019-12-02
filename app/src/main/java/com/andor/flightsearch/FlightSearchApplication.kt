package com.andor.flightsearch

import android.app.Application
import com.andor.flightsearch.core.dependencyinjection.application.ApplicationComponent
import com.andor.flightsearch.core.dependencyinjection.application.ApplicationModule
import com.andor.flightsearch.core.dependencyinjection.application.DaggerApplicationComponent

class FlightSearchApplication : Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return mApplicationComponent
    }
}