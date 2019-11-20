package com.andor.flightsearch.repo

import com.andor.flightsearch.model.flightModel.FlightDetails
import com.andor.weatherapp.repo.Resource

interface Repository {
    suspend fun getFlightList(): Resource<FlightDetails>
}
