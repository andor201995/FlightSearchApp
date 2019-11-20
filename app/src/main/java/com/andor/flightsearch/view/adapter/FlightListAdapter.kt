package com.andor.flightsearch.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.R
import com.andor.flightsearch.core.MyDiffCallback
import com.andor.flightsearch.model.flightModel.Airlines
import com.andor.flightsearch.model.flightModel.Appendix
import com.andor.flightsearch.model.flightModel.Fare
import com.andor.flightsearch.model.flightModel.Flight
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FlightListAdapter(private val context: Context) :
    RecyclerView.Adapter<FlightListAdapter.FlightHolder>() {

    lateinit var appendix: Appendix
    lateinit var flightList: ArrayList<Flight>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.flight_item, parent, false)
        return FlightHolder(itemView)
    }

    override fun getItemCount(): Int {
        return flightList.size
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: FlightHolder, position: Int) {

        holder.airlineNameTxt.text = when (flightList[position].airlineCode) {
            Airlines.airlineCode1 -> {
                appendix.airlines.`6E`
            }
            Airlines.airlineCode2 -> {
                appendix.airlines.`9W`
            }
            Airlines.airlineCode3 -> {
                appendix.airlines.AI
            }
            Airlines.airlineCode4 -> {
                appendix.airlines.G8
            }
            Airlines.airlineCode5 -> {
                appendix.airlines.SG
            }
            else -> {
                "Unknown"
            }
        }

        holder.airlineClassTxt.text = flightList[position].`class`

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = flightList[position].arrivalTime

        val dateFormat = SimpleDateFormat(
            "hh:mm a"
        )

        holder.arrivalTimeTxt.text =
            DateFormat.getDateInstance().format(Date(flightList[position].arrivalTime)) + "\n" + dateFormat.format(
                calendar.time
            )

        calendar.timeInMillis = flightList[position].departureTime
        holder.depTimeTxt.text =
            DateFormat.getDateInstance().format(Date(flightList[position].departureTime)) + "\n" + dateFormat.format(
                calendar.time
            )

        holder.destinationCodeTxt.text = flightList[position].destinationCode
        holder.originCodeTxt.text = flightList[position].originCode

        //setting up fare RecyclerView
        setFareListRecyclerView(position, holder)
    }

    private fun setFareListRecyclerView(
        position: Int,
        holder: FlightHolder
    ) {
        val adapter = FareListAdapter()
        val sortFareList = sortFareList(flightList[position].fares)

        adapter.fareList = sortFareList
        adapter.appendix = appendix

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL

        holder.fareListView.adapter = adapter
        holder.fareListView.layoutManager = linearLayoutManager
    }

    private fun sortFareList(fareList: List<Fare>): ArrayList<Fare> {
        return ArrayList(fareList.sortedBy { it.fare })
    }

    fun updateList(newFlights: List<Flight>) {
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

    inner class FlightHolder(view: View) : RecyclerView.ViewHolder(view) {
        val airlineNameTxt: TextView = view.findViewById(R.id.txt_airline_name)
        val airlineClassTxt: TextView = view.findViewById(R.id.txt_airline_class)
        val arrivalTimeTxt: TextView = view.findViewById(R.id.txt_arrival_time)
        val depTimeTxt: TextView = view.findViewById(R.id.txt_departure_time)
        val destinationCodeTxt: TextView = view.findViewById(R.id.txt_dest_code)
        val originCodeTxt: TextView = view.findViewById(R.id.txt_origin_code)
        val fareListView: RecyclerView = view.findViewById(R.id.fareList)

    }
}