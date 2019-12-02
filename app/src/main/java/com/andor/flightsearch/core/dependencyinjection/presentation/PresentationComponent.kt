package com.andor.flightsearch.core.dependencyinjection.presentation

import com.andor.flightsearch.screens.errorfetch.ErrorFragment
import com.andor.flightsearch.screens.flightlist.ShowFlightListFragment
import com.andor.flightsearch.screens.selectflight.SelectFlightFragment
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(errorFragment: ErrorFragment)
    fun inject(selectFlightFragment: SelectFlightFragment)
    fun inject(showFlightListFragment: ShowFlightListFragment)
}
