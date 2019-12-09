package com.andor.flightsearch.screens.common.viewmodel

import com.andor.flightsearch.flight.FlightDetail

data class AppState(
    val flightResponseType: FlightResponseType = FlightResponseType.Loading,
    val sortingType: SortingType = SortingType.Fare
)

sealed class FlightResponseType {
    object Loading : FlightResponseType()
    data class Success(val flightDetailList: List<FlightDetail>) : FlightResponseType()
    object Failure : FlightResponseType()
}

sealed class SortingType {
    object ArrTime : SortingType()
    object DeptTime : SortingType()
    object Fare : SortingType()
}

