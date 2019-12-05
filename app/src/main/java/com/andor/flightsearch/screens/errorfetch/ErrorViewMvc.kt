package com.andor.flightsearch.screens.errorfetch

import com.andor.flightsearch.screens.common.views.ObservableViewMvc

interface ErrorViewMvc : ObservableViewMvc<ErrorViewMvc.Listener> {
    interface Listener {
        fun onRefreshed()
    }

    fun displayErrorMessage(message: String?)
    fun stopRefreshing()

}