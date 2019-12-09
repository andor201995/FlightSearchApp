package com.andor.flightsearch.flight

interface FetchFlightListUseCase {
    interface Listener {
        fun onFlightDetailsFetched(flightDetailList: List<FlightDetail>)
        fun onFetchFailed()
    }

    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun fetchQuestionList()
}