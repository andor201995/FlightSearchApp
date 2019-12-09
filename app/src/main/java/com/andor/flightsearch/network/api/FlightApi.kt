package com.andor.flightsearch.network.api

import com.andor.flightsearch.network.flight.flightSchema.FlightSchema
import retrofit2.Call
import retrofit2.http.GET

interface FlightApi {

    @GET("5979c6731100001e039edcb3")
    fun getFlightDetails(): Call<FlightSchema>

}