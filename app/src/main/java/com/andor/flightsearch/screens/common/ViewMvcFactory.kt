package com.andor.flightsearch.screens.common

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import com.andor.flightsearch.screens.common.toolbar.ToolbarMvc
import com.andor.flightsearch.screens.flightlist.ShowFlightListMvc
import com.andor.flightsearch.screens.flightlist.ShowFlightListMvcImpl
import com.andor.flightsearch.screens.selectflight.SelectFlightViewMvc
import com.andor.flightsearch.screens.selectflight.SelectFlightViewMvcImpl

class ViewMvcFactory(
    private val inflater: LayoutInflater
) {

    fun getSelectFlightViewMvc(parent: ViewGroup): SelectFlightViewMvc {
        return SelectFlightViewMvcImpl(inflater, parent)
    }

    fun getShowFlightListMvc(parent: ViewGroup): ShowFlightListMvc {
        return ShowFlightListMvcImpl(inflater, parent)
    }

    fun getToolBarMvc(menuInflater: MenuInflater, menu: Menu): ToolbarMvc {
        return ToolbarMvc(menuInflater, menu)
    }

}