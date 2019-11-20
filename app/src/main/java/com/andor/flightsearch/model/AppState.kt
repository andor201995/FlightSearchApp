package com.andor.flightsearch.model

import com.andor.flightsearch.model.flightModel.FlightDetails
import com.andor.weatherapp.repo.Resource

data class AppState(
    val flightDetailsResource: Resource<FlightDetails> = Resource.loading(data = null),
    val sortingType: SortingType = SortingType.Fare
)


sealed class SortingType {
    object ArrTime : SortingType()
    object DeptTime : SortingType()
    object Fare : SortingType()
}