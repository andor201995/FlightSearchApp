package com.andor.flightsearch.network.flight.flightSchema

data class FlightSchema(
    val appendix: Appendix = Appendix(),
    val flights: List<Flight> = listOf()
)