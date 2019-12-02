package com.andor.flightsearch.screens.common.screennavigator

import android.app.Activity
import androidx.navigation.Navigation.findNavController
import com.andor.flightsearch.R

class ScreenNavigator(private val activity: Activity) {

    val navHostController = findNavController(activity, R.id.nav_host)

    fun navigateFromSelectedFlightToShowFlight() {
        navHostController
            .navigate(R.id.action_mainFragment_to_showFlightList)
    }

    fun navigateFromShowFlightListToErrorPage() {
        navHostController
            .navigate(R.id.action_showFlightList_to_errorFragment)
    }

}