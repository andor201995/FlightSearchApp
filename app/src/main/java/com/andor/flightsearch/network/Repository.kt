package com.andor.flightsearch.network

import com.andor.flightsearch.flight.flightSchema.FlightDetails
import com.andor.flightsearch.network.response.Resource

interface Repository {
    suspend fun getFlightList(): Resource<FlightDetails>
}
