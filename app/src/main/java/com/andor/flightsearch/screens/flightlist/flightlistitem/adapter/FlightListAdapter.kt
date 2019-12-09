package com.andor.flightsearch.screens.flightlist.flightlistitem.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.flight.FlightDetail
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.flightlist.flightlistitem.FlightListItemMvc


class FlightListAdapter(private val viewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<FlightListAdapter.FlightHolder>() {

    private val flightList: ArrayList<FlightDetail> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        return FlightHolder(viewMvcFactory.getFlightListItemMvc(parent))
    }

    override fun getItemCount(): Int {
        return flightList.size
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        holder.flightListItemMvc.bindFlight(flightList[position])
    }

    fun updateList(
        newFlights: List<FlightDetail>
    ) {
        val diffResult = DiffUtil.calculateDiff(
            MyDiffCallback(
                this.flightList,
                newFlights
            )
        )
        flightList.clear()
        flightList.addAll(newFlights)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class FlightHolder(val flightListItemMvc: FlightListItemMvc) :
        RecyclerView.ViewHolder(flightListItemMvc.rootView)
}