package com.andor.flightsearch.flight.flightSchema

data class Flight(
    val airlineCode: String,
    val arrivalTime: Long,
    val `class`: String,
    val departureTime: Long,
    val destinationCode: String,
    val fares: List<Fare>,
    val originCode: String
) {
    fun getBestPriceProvider(): Fare {
        return fares.sortedBy { it.fare }[0]
    }
}