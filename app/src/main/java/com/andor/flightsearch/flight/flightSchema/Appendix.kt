package com.andor.flightsearch.flight.flightSchema

data class Appendix(
    val airlines: Airlines = Airlines(),
    val airports: Airports = Airports(),
    val providers: Providers = Providers()
)