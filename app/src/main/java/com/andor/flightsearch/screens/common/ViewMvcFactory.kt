package com.andor.flightsearch.screens.common

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import com.andor.flightsearch.screens.flightlist.ShowFlightListMvc
import com.andor.flightsearch.screens.flightlist.ShowFlightListMvcImpl
import com.andor.flightsearch.screens.flightlist.dialog.SettingBottomSheetMvc
import com.andor.flightsearch.screens.flightlist.dialog.SettingBottomSheetMvcImpl
import com.andor.flightsearch.screens.flightlist.flightlistitem.FareListItemMvc
import com.andor.flightsearch.screens.flightlist.flightlistitem.FareListItemMvcImpl
import com.andor.flightsearch.screens.flightlist.flightlistitem.FlightListItemMvc
import com.andor.flightsearch.screens.flightlist.flightlistitem.FlightListItemMvcImpl
import com.andor.flightsearch.screens.flightlist.toolbar.ToolbarMvc
import com.andor.flightsearch.screens.selectflight.SelectFlightViewMvc
import com.andor.flightsearch.screens.selectflight.SelectFlightViewMvcImpl

class ViewMvcFactory(
    private val inflater: LayoutInflater
) {

    fun getSelectFlightViewMvc(parent: ViewGroup?): SelectFlightViewMvc {
        return SelectFlightViewMvcImpl(inflater, parent)
    }

    fun getShowFlightListMvc(parent: ViewGroup?): ShowFlightListMvc {
        return ShowFlightListMvcImpl(inflater, parent, this)
    }

    fun getToolBarMvc(menuInflater: MenuInflater, menu: Menu): ToolbarMvc {
        return ToolbarMvc(
            menuInflater,
            menu
        )
    }

    fun getFlightListItemMvc(parent: ViewGroup): FlightListItemMvc {
        return FlightListItemMvcImpl(inflater, parent, this)
    }

    fun getFareListItemMvc(parent: ViewGroup): FareListItemMvc {
        return FareListItemMvcImpl(inflater, parent)
    }

    fun getSettingBottomSheetMvc(
        parent: ViewGroup?
    ): SettingBottomSheetMvc {
        return SettingBottomSheetMvcImpl(inflater, parent)
    }

}