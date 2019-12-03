package com.andor.flightsearch.screens.flightlist

import com.andor.flightsearch.flight.flightSchema.Appendix
import com.andor.flightsearch.flight.flightSchema.Flight
import com.andor.flightsearch.screens.common.views.ViewMvc

interface ShowFlightListMvc : ViewMvc {

    fun startShimmer()
    fun stopShimmer()
    fun updateFlightList(
        flights: List<Flight>,
        appendix: Appendix
    )
    fun scrollListToTop()
}