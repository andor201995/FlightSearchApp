package com.andor.flightsearch.screens.common.screennavigator

import android.app.Activity
import androidx.navigation.Navigation
import com.andor.flightsearch.R

class ScreenNavigator(private val activity: Activity) {

    fun navigateFromSelectedFlightToShowFlight() {
        Navigation.findNavController(activity, R.id.nav_host)
            .navigate(R.id.action_mainFragment_to_showFlightList)
    }

}