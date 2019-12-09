package com.andor.flightsearch.screens.flightlist.flightlistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.andor.flightsearch.R
import com.andor.flightsearch.flight.FlightFareDetail
import com.andor.flightsearch.screens.common.views.BaseViewMvc

class FareListItemMvcImpl(inflater: LayoutInflater, parent: ViewGroup) : BaseViewMvc(),
    FareListItemMvc {
    private val fareProviderTxt: TextView
    private val fareTxt: TextView

    init {
        rootView = inflater.inflate(R.layout.fare_item_view, parent, false)

        fareProviderTxt = findViewById(R.id.txt_fare_provider)
        fareTxt = findViewById(R.id.txt_fare)
    }

    override fun bindFare(fare: FlightFareDetail) {
        fareProviderTxt.text = fare.providerName
        fareTxt.text = fare.costOfFlight.toString()
    }
}