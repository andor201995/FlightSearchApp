package com.andor.flightsearch.screens.flightlist.flightlistitem.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.flight.flightSchema.Appendix
import com.andor.flightsearch.flight.flightSchema.Flight
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.flightlist.flightlistitem.FlightListItemMvc


class FlightListAdapter(private val viewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<FlightListAdapter.FlightHolder>() {

    private var appendix: Appendix = Appendix()
    private val flightList: ArrayList<Flight> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        return FlightHolder(viewMvcFactory.getFlightListItemMvc(parent))
    }

    override fun getItemCount(): Int {
        return flightList.size
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        holder.flightListItemMvc.bindFlight(flightList[position], appendix)
    }

    fun updateList(
        newFlights: List<Flight>,
        appendix: Appendix
    ) {
        val diffResult = DiffUtil.calculateDiff(
            MyDiffCallback(
                this.flightList,
                newFlights
            )
        )
        flightList.clear()
        flightList.addAll(newFlights)
        this.appendix = appendix
        diffResult.dispatchUpdatesTo(this)
    }

    inner class FlightHolder(val flightListItemMvc: FlightListItemMvc) :
        RecyclerView.ViewHolder(flightListItemMvc.rootView)
}