package com.andor.flightsearch.repo

import com.andor.flightsearch.model.flightModel.FlightDetails
import com.andor.flightsearch.repo.api.FlightApi
import com.andor.flightsearch.repo.response.Resource
import com.andor.flightsearch.repo.response.ResponseHandler


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
