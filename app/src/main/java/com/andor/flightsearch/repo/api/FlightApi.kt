package com.andor.flightsearch.repo.api

import com.andor.flightsearch.model.flightModel.FlightDetails
import retrofit2.http.GET

interface FlightApi {

    @GET("5979c6731100001e039edcb3")
    suspend fun getFlightDetails(): FlightDetails

}