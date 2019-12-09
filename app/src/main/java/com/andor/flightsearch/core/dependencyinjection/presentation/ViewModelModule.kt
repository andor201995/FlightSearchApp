package com.andor.flightsearch.core.dependencyinjection.presentation

import androidx.lifecycle.ViewModel
import com.andor.flightsearch.flight.FetchFlightListUseCase
import com.andor.flightsearch.flight.FetchFlightListUseCaseImpl
import com.andor.flightsearch.network.flight.FetchFlightListEndPoint
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class ViewModelModule {
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(FlightSearchViewModel::class)
    fun flightSearchViewModel(fetchFlightListUseCase: FetchFlightListUseCase): ViewModel {
        return FlightSearchViewModel(fetchFlightListUseCase)
    }
}