package com.andor.flightsearch.screens.flightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.flightsearch.R
import com.andor.flightsearch.core.invisible
import com.andor.flightsearch.core.visible
import com.andor.flightsearch.screens.common.views.BaseViewMvc
import com.facebook.shimmer.ShimmerFrameLayout

class ShowFlightListMvcImpl(inflater: LayoutInflater, parent: ViewGroup) : BaseViewMvc(),
    ShowFlightListMvc {

    init {
        rootView = inflater.inflate(R.layout.fragment_show_flight_list, parent, false)
    }

    override fun startShimmer() {
        val shimmerFrameLayout =
            rootView.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container)
        shimmerFrameLayout.visible()
        shimmerFrameLayout.startShimmer()
    }

    override fun stopShimmer() {
        val shimmerFrameLayout =
            rootView.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container)
        shimmerFrameLayout.invisible()
        shimmerFrameLayout.stopShimmer()
    }


}