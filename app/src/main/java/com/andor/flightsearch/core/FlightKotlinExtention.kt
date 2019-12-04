package com.andor.flightsearch.core

import android.view.View
import androidx.navigation.NavController

fun View.invisible() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun NavController.navigateSafe(
    currentID: Int,
    actionID: Int
) {
    if (currentDestination!!.id == currentID) {
        navigate(actionID)
    }

}