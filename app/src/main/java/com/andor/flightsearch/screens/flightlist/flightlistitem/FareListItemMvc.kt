package com.andor.flightsearch.screens.flightlist.flightlistitem

import com.andor.flightsearch.flight.flightSchema.Appendix
import com.andor.flightsearch.flight.flightSchema.Fare
import com.andor.flightsearch.screens.common.views.ViewMvc

interface FareListItemMvc : ViewMvc {
    fun bindFare(fare: Fare, appendix: Appendix)
}