package com.andor.flightsearch.model.flightModel

data class FlightDetails(
    val appendix: Appendix = Appendix(),
    val flights: List<Flight> = listOf()
)