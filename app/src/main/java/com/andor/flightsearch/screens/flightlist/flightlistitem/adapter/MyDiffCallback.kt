package com.andor.flightsearch.screens.flightlist.flightlistitem.adapter

import androidx.recyclerview.widget.DiffUtil
import com.andor.flightsearch.flight.FlightDetail

class MyDiffCallback(
    private val oldFlightList: ArrayList<FlightDetail>,
    private val newFlightList: List<FlightDetail>
) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFlightList[oldItemPosition] == newFlightList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldFlightList.size
    }

    override fun getNewListSize(): Int {
        return newFlightList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFlightList[oldItemPosition].airlineName == newFlightList[newItemPosition].airlineName &&
                oldFlightList[oldItemPosition].flightArrivalTimeStamp == newFlightList[newItemPosition].flightArrivalTimeStamp &&
                oldFlightList[oldItemPosition].flightDepartureTimeStamp == newFlightList[newItemPosition].flightDepartureTimeStamp &&
                oldFlightList[oldItemPosition].classOfSeatInFlight == newFlightList[newItemPosition].classOfSeatInFlight &&
                oldFlightList[oldItemPosition].originCode == newFlightList[newItemPosition].originCode &&
                oldFlightList[oldItemPosition].destinationCode == newFlightList[newItemPosition].destinationCode
    }

}