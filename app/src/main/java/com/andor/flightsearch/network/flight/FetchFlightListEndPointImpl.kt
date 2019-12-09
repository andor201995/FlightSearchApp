package com.andor.flightsearch.network.flight

import com.andor.flightsearch.network.api.FlightApi
import com.andor.flightsearch.network.flight.flightSchema.FlightSchema
import com.andor.flightsearch.network.response.ResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FetchFlightListEndPointImpl(
    private val flightApi: FlightApi,
    private val responseHandler: ResponseHandler
) : FetchFlightListEndPoint {

    override fun fetchFlightListAndNotify(listener: FetchFlightListEndPoint.Listener) {
        val call = flightApi.getFlightDetails()

        call.enqueue(object : Callback<FlightSchema> {
            override fun onFailure(call: Call<FlightSchema>, t: Throwable) {
                listener.handleResponse(responseHandler.handleException(Exception(t)))
            }

            override fun onResponse(call: Call<FlightSchema>, response: Response<FlightSchema>) {
                if (response.isSuccessful) {
                    listener.handleResponse(responseHandler.handleSuccess(response.body()!!))
                } else {
                    listener.handleResponse(responseHandler.handleException(Exception("Response Failure")))
                }
            }

        })
    }

}
