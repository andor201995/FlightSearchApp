package com.andor.flightsearch.core.dependencyinjection.application

import com.andor.flightsearch.core.dependencyinjection.presentation.PresentationComponent
import com.andor.flightsearch.core.dependencyinjection.presentation.PresentationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun getPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}