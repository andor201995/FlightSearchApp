package com.andor.flightsearch.core.dependencyinjection.application

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    fun getApplication(): Application {
        return mApplication
    }
}