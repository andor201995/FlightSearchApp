package com.andor.flightsearch.screens.flightlist.flightlistitem

import com.andor.flightsearch.flight.FlightDetail
import com.andor.flightsearch.screens.common.views.ViewMvc

interface FlightListItemMvc : ViewMvc {
    fun bindFlight(
        flight: FlightDetail
    )
}