package com.andor.flightsearch.screens.flightlist.flightlistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.andor.flightsearch.R
import com.andor.flightsearch.flight.flightSchema.Appendix
import com.andor.flightsearch.flight.flightSchema.Fare
import com.andor.flightsearch.flight.flightSchema.Providers
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

    override fun bindFare(fare: Fare, appendix: Appendix) {
        fareProviderTxt.text = when (fare.providerId) {
            Providers.providerCode1 -> {
                appendix.providers.`1`
            }
            Providers.providerCode2 -> {
                appendix.providers.`2`
            }
            Providers.providerCode3 -> {
                appendix.providers.`3`
            }
            Providers.providerCode4 -> {
                appendix.providers.`4`
            }
            else -> "Unknown"
        }
        fareTxt.text = fare.fare.toString()
    }
}