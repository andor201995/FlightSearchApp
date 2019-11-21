package com.andor.flightsearch.repo

import com.andor.flightsearch.model.flightModel.FlightDetails
import com.andor.flightsearch.repo.response.Resource

interface Repository {
    suspend fun getFlightList(): Resource<FlightDetails>
}
