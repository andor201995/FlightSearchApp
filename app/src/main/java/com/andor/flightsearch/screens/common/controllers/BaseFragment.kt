package com.andor.flightsearch.screens.common.controllers

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.andor.flightsearch.FlightSearchApplication
import com.andor.flightsearch.core.dependencyinjection.application.ApplicationComponent
import com.andor.flightsearch.core.dependencyinjection.presentation.PresentationComponent
import com.andor.flightsearch.core.dependencyinjection.presentation.PresentationModule

open class BaseFragment : Fragment() {

    private var mIsInjectorUsed: Boolean = false

    @get:UiThread
    protected val presentationComponent: PresentationComponent
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent
                .getPresentationComponent(PresentationModule(activity!!))
        }

    private val applicationComponent: ApplicationComponent
        get() = (activity!!.application as FlightSearchApplication).getApplicationComponent()
}