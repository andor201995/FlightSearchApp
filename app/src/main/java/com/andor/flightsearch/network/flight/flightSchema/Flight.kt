package com.andor.flightsearch.network.flight.flightSchema

data class Flight(
    val airlineCode: String = "",
    val arrivalTime: Long = 0,
    val `class`: String = "",
    val departureTime: Long = 0,
    val destinationCode: String = "",
    val fares: List<Fare> = listOf(),
    val originCode: String = ""
) {
    fun getBestPriceProvider(): Fare {
        return fares.sortedBy { it.fare }[0]
    }
}