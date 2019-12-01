package com.andor.flightsearch.flight.flightSchema

data class FlightDetails(
    val appendix: Appendix = Appendix(),
    val flights: List<Flight> = listOf()
)