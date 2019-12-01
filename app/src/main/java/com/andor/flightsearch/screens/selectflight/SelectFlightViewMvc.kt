package com.andor.flightsearch.screens.selectflight

import com.andor.flightsearch.screens.common.views.ObservableViewMvc

interface SelectFlightViewMvc : ObservableViewMvc<SelectFlightViewMvc.Listener> {
    interface Listener {
        fun onButtonClicked()
    }
}