package com.andor.flightsearch.screens.flightlist

import com.andor.flightsearch.flight.FlightDetail
import com.andor.flightsearch.network.flight.flightSchema.Appendix
import com.andor.flightsearch.screens.common.views.ViewMvc

interface ShowFlightListMvc : ViewMvc {

    fun startShimmer()
    fun stopShimmer()
    fun updateFlightList(
        flights: List<FlightDetail>
    )
    fun scrollListToTop()
}