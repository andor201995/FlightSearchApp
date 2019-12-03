package com.andor.flightsearch.screens.flightlist.toolbar

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.andor.flightsearch.R
import com.andor.flightsearch.core.BaseObservable

class ToolbarMvc(private val menuInflater: MenuInflater, private val menu: Menu) :
    BaseObservable<ToolbarMvc.Listener>() {

    interface Listener {
        fun onSettingOptionSelected()
    }

    init {
        inflateMenu()
    }

    private fun inflateMenu() {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    fun onOptionItemSelected(item: MenuItem) {
        if (item.itemId == R.id.action_setting) {
            for (listener in listeners) {
                listener.onSettingOptionSelected()
            }
        }
    }


}