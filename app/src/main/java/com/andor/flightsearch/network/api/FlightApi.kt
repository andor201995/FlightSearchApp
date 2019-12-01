package com.andor.flightsearch.network.api

import com.andor.flightsearch.flight.flightSchema.FlightDetails
import retrofit2.http.GET

interface FlightApi {

    @GET("5979c6731100001e039edcb3")
    suspend fun getFlightDetails(): FlightDetails

}