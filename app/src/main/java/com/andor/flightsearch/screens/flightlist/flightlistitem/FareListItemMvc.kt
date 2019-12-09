package com.andor.flightsearch.screens.flightlist.flightlistitem

import com.andor.flightsearch.flight.FlightFareDetail
import com.andor.flightsearch.network.flight.flightSchema.Appendix
import com.andor.flightsearch.screens.common.views.ViewMvc

interface FareListItemMvc : ViewMvc {
    fun bindFare(fare: FlightFareDetail)
}