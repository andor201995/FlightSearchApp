package com.andor.flightsearch.model.flightModel

data class Appendix(
    val airlines: Airlines = Airlines(),
    val airports: Airports = Airports(),
    val providers: Providers = Providers()
)