package com.andor.flightsearch.screens.common.screennavigator

import android.app.Activity
import androidx.navigation.Navigation
import com.andor.flightsearch.R

class ScreenNavigator(private val activity: Activity) {

    fun navigateFromSelectedFlightToShowFlight() {
        Navigation.findNavController(activity, R.id.nav_host)
            .navigate(R.id.action_mainFragment_to_showFlightList)
    }

    fun navigateFromShowFlightListToErrorPage() {
        Navigation.findNavController(activity, R.id.nav_host)
            .navigate(R.id.action_showFlightList_to_errorFragment)
    }

    fun navigateFromSHowFlightToSettingDialog() {
        Navigation.findNavController(activity, R.id.nav_host)
            .navigate(R.id.action_showFlightList_to_settingBottomSheetFragment)
    }

    fun navigateUp() {
        Navigation.findNavController(activity, R.id.nav_host).navigateUp()

    }

}