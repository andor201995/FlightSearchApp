package com.andor.flightsearch.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.flightsearch.screens.selectflight.SelectFlightViewMvc
import com.andor.flightsearch.screens.selectflight.SelectFlightViewMvcImpl

class ViewMvcFactory(private val inflater: LayoutInflater) {

    fun getSelectFlightViewMvc(parent: ViewGroup): SelectFlightViewMvc {
        return SelectFlightViewMvcImpl(inflater, parent)
    }

}