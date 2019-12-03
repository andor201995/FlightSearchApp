package com.andor.flightsearch.screens.flightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.R
import com.andor.flightsearch.core.invisible
import com.andor.flightsearch.core.visible
import com.andor.flightsearch.flight.flightSchema.Appendix
import com.andor.flightsearch.flight.flightSchema.Flight
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.views.BaseViewMvc
import com.andor.flightsearch.screens.flightlist.flightlistitem.adapter.FlightListAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class ShowFlightListMvcImpl(
    inflater: LayoutInflater,
    parent: ViewGroup,
    viewMvcFactory: ViewMvcFactory
) : BaseViewMvc(),
    ShowFlightListMvc {

    private lateinit var adapter: FlightListAdapter
    private lateinit var flightListRecyclerView: RecyclerView

    init {
        rootView = inflater.inflate(R.layout.fragment_show_flight_list, parent, false)
        setFlightListRecyclerView(viewMvcFactory)
    }

    private fun setFlightListRecyclerView(viewMvcFactory: ViewMvcFactory) {
        flightListRecyclerView = findViewById(R.id.flight_list)
        adapter = FlightListAdapter(viewMvcFactory)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL

        flightListRecyclerView.layoutManager = linearLayoutManager
        flightListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        flightListRecyclerView.adapter = adapter
    }

    override fun startShimmer() {
        val shimmerFrameLayout =
            findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container)
        shimmerFrameLayout.visible()
        shimmerFrameLayout.startShimmer()
    }

    override fun stopShimmer() {
        val shimmerFrameLayout =
            findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container)
        shimmerFrameLayout.invisible()
        shimmerFrameLayout.stopShimmer()
    }

    override fun updateFlightList(
        flights: List<Flight>,
        appendix: Appendix
    ) {
        adapter.updateList(flights, appendix)
    }

    override fun scrollListToTop() {
        flightListRecyclerView.layoutManager!!.scrollToPosition(0)
    }


}