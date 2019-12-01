package com.andor.flightsearch.network

import com.andor.flightsearch.flight.flightSchema.FlightDetails
import com.andor.flightsearch.network.api.FlightApi
import com.andor.flightsearch.network.response.Resource
import com.andor.flightsearch.network.response.ResponseHandler


class RepositoryImpl(
    private val flightApi: FlightApi,
    private val responseHandler: ResponseHandler
) : Repository {

    override suspend fun getFlightList(): Resource<FlightDetails> {
        return try {
            responseHandler.handleSuccess(flightApi.getFlightDetails())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}
