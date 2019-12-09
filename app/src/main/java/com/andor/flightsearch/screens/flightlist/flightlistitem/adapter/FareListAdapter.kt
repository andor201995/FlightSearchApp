package com.andor.flightsearch.screens.flightlist.flightlistitem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.flight.FlightFareDetail
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.flightlist.flightlistitem.FareListItemMvc

class FareListAdapter(private val viewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<FareListAdapter.FareHolder>() {
    lateinit var fareList: ArrayList<FlightFareDetail>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FareHolder {
        return FareHolder(viewMvcFactory.getFareListItemMvc(parent))
    }

    override fun getItemCount(): Int {
        return fareList.size
    }

    override fun onBindViewHolder(holder: FareHolder, position: Int) {
        holder.fareListMvc.bindFare(fareList[position])
    }

    inner class FareHolder(val fareListMvc: FareListItemMvc) :
        RecyclerView.ViewHolder(fareListMvc.rootView)
}
