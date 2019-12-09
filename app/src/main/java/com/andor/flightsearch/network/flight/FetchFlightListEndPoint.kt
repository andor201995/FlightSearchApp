package com.andor.flightsearch.network.flight

import com.andor.flightsearch.network.flight.flightSchema.FlightSchema
import com.andor.flightsearch.network.response.Resource

interface FetchFlightListEndPoint {
    interface Listener {
        fun handleResponse(response: Resource<FlightSchema>)
    }

    fun fetchFlightListAndNotify(listener: Listener)
}
