package com.andor.flightsearch.screens.common.screennavigator

import android.app.Activity
import androidx.navigation.Navigation.findNavController
import com.andor.flightsearch.R
import com.andor.flightsearch.core.navigateSafe

class ScreenNavigator(private val activity: Activity) {

    fun navigateFromSelectedFlightToShowFlight() {
        findNavController(activity, R.id.nav_host)
            .navigateSafe(R.id.selectFlightFragment, R.id.action_selectFlightFragment_to_showFlightList)
    }

    fun navigateFromShowFlightListToErrorPage() {
        findNavController(activity, R.id.nav_host)
            .navigateSafe(R.id.showFlightList, R.id.action_showFlightList_to_errorFragment)
    }

    fun navigateFromSHowFlightToSettingDialog() {
        findNavController(activity, R.id.nav_host)
            .navigateSafe(
                R.id.showFlightList,
                R.id.action_showFlightList_to_settingBottomSheetFragment
            )
    }

    fun navigateUp() {
        findNavController(activity, R.id.nav_host).navigateUp()
    }
}

