package com.andor.flightsearch.screens.flightlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.andor.flightsearch.flight.flightSchema.Flight

class MyDiffCallback(
    private val oldFlightList: List<Flight>,
    private val newFlightList: List<Flight>
) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFlightList[oldItemPosition].airlineCode == newFlightList[newItemPosition].airlineCode &&
                oldFlightList[oldItemPosition].arrivalTime == newFlightList[newItemPosition].arrivalTime &&
                oldFlightList[oldItemPosition].departureTime == newFlightList[newItemPosition].departureTime &&
                oldFlightList[oldItemPosition].`class` == newFlightList[newItemPosition].`class` &&
                oldFlightList[oldItemPosition].originCode == newFlightList[newItemPosition].originCode &&
                oldFlightList[oldItemPosition].destinationCode == newFlightList[newItemPosition].destinationCode
    }

    override fun getOldListSize(): Int {
        return oldFlightList.size
    }

    override fun getNewListSize(): Int {
        return newFlightList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFlightList[oldItemPosition] == newFlightList[newItemPosition]
    }

}