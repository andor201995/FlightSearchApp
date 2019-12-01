package com.andor.flightsearch.screens.common

import com.andor.flightsearch.flight.flightSchema.FlightDetails
import com.andor.flightsearch.network.response.Resource

data class AppState(
    val flightDetailsResource: Resource<FlightDetails> = Resource.loading(data = null),
    val sortingType: SortingType = SortingType.Fare
)


sealed class SortingType {
     object ArrTime : SortingType()
    object DeptTime : SortingType()
    object Fare : SortingType()
}