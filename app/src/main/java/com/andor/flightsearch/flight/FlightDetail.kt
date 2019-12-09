package com.andor.flightsearch.flight

data class FlightDetail(
    val airlineName: String = "",
    val classOfSeatInFlight: String = "",
    val flightArrivalTimeStamp: Long = 0,
    val flightDepartureTimeStamp: Long = 0,
    val destinationCode: String = "",
    val originCode: String = "",
    val flightFareList: List<FlightFareDetail> = listOf()
) {
    fun getBestPriceProvider(): FlightFareDetail {
        return flightFareList.sortedBy { it.costOfFlight }[0]
    }
}

data class FlightFareDetail(
    val costOfFlight: Long = 0,
    val providerName: String = ""
)