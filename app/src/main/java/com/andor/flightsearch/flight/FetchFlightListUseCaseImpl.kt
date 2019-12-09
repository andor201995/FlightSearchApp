package com.andor.flightsearch.flight

import com.andor.flightsearch.core.BaseObservable
import com.andor.flightsearch.network.flight.FetchFlightListEndPoint
import com.andor.flightsearch.network.flight.flightSchema.Airlines
import com.andor.flightsearch.network.flight.flightSchema.FlightSchema
import com.andor.flightsearch.network.flight.flightSchema.Providers
import com.andor.flightsearch.network.response.Resource
import com.andor.flightsearch.network.response.Status

class FetchFlightListUseCaseImpl(private val fetchFlightListEndPoint: FetchFlightListEndPoint) :
    BaseObservable<FetchFlightListUseCase.Listener>(), FetchFlightListUseCase,
    FetchFlightListEndPoint.Listener {

    override fun fetchQuestionList() {
        fetchFlightListEndPoint.fetchFlightListAndNotify(this)
    }

    override fun handleResponse(response: Resource<FlightSchema>) {
        listeners.forEach {
            when (response.status) {
                is Status.Success -> {
                    it.onFlightDetailsFetched(flightDetailsFrom(response.data!!))
                }
                is Status.Error -> {
                    it.onFetchFailed()
                }
            }
        }
    }


    private fun flightDetailsFrom(flightSchema: FlightSchema): List<FlightDetail> {
        val flightDetailList = arrayListOf<FlightDetail>()
        flightSchema.flights.forEach { flight ->
            val flightFareList = arrayListOf<FlightFareDetail>()
            flight.fares.forEach { fare ->
                flightFareList.add(
                    FlightFareDetail(
                        costOfFlight = fare.fare.toLong(),
                        providerName = when (fare.providerId) {
                            Providers.providerCode1 -> {
                                flightSchema.appendix.providers.`1`
                            }
                            Providers.providerCode2 -> {
                                flightSchema.appendix.providers.`2`
                            }
                            Providers.providerCode3 -> {
                                flightSchema.appendix.providers.`3`
                            }
                            Providers.providerCode4 -> {
                                flightSchema.appendix.providers.`4`
                            }
                            else -> "Unknown"
                        }

                    )
                )
            }

            flightDetailList.add(

                FlightDetail(
                    airlineName = when (flight.airlineCode) {
                        Airlines.airlineCode1 -> {
                            flightSchema.appendix.airlines.`6E`
                        }
                        Airlines.airlineCode2 -> {
                            flightSchema.appendix.airlines.`9W`
                        }
                        Airlines.airlineCode3 -> {
                            flightSchema.appendix.airlines.AI
                        }
                        Airlines.airlineCode4 -> {
                            flightSchema.appendix.airlines.G8
                        }
                        Airlines.airlineCode5 -> {
                            flightSchema.appendix.airlines.SG
                        }
                        else -> {
                            "Unknown"
                        }
                    },
                    classOfSeatInFlight = flight.`class`,
                    destinationCode = flight.destinationCode,
                    originCode = flight.originCode,
                    flightArrivalTimeStamp = flight.arrivalTime,
                    flightDepartureTimeStamp = flight.departureTime,
                    flightFareList = flightFareList
                )
            )

        }
        return flightDetailList
    }

}