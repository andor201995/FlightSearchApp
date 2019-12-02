package com.andor.flightsearch.screens.flightlist

import com.andor.flightsearch.screens.common.views.ViewMvc

interface ShowFlightListMvc : ViewMvc {

    fun startShimmer()
    fun stopShimmer()

}