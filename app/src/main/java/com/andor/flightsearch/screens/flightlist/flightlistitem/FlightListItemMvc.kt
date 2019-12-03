package com.andor.flightsearch.screens.flightlist.flightlistitem

import com.andor.flightsearch.flight.flightSchema.Appendix
import com.andor.flightsearch.flight.flightSchema.Flight
import com.andor.flightsearch.screens.common.views.ViewMvc

interface FlightListItemMvc : ViewMvc {
    fun bindFlight(
        flight: Flight,
        appendix: Appendix
    )
}