package com.andor.flightsearch.screens.flightlist.flightlistitem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.R
import com.andor.flightsearch.flight.flightSchema.Airlines
import com.andor.flightsearch.flight.flightSchema.Appendix
import com.andor.flightsearch.flight.flightSchema.Fare
import com.andor.flightsearch.flight.flightSchema.Flight
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.views.BaseViewMvc
import com.andor.flightsearch.screens.flightlist.flightlistitem.adapter.FareListAdapter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FlightListItemMvcImpl(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val viewMvcFactory: ViewMvcFactory
) : BaseViewMvc(),
    FlightListItemMvc {

    private val airlineNameTxt: TextView
    private val airlineClassTxt: TextView
    private val arrivalTimeTxt: TextView
    private val depTimeTxt: TextView
    private val destinationCodeTxt: TextView
    private val originCodeTxt: TextView
    private val fareListView: RecyclerView

    init {
        rootView = inflater.inflate(R.layout.flight_item, parent, false)

        airlineNameTxt = findViewById(R.id.txt_airline_name)
        airlineClassTxt = findViewById(R.id.txt_airline_class)
        arrivalTimeTxt = findViewById(R.id.txt_arrival_time)
        depTimeTxt = findViewById(R.id.txt_departure_time)
        destinationCodeTxt = findViewById(R.id.txt_dest_code)
        originCodeTxt = findViewById(R.id.txt_origin_code)
        fareListView = findViewById(R.id.fareList)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun bindFlight(
        flight: Flight,
        appendix: Appendix
    ) {

        airlineNameTxt.text = when (flight.airlineCode) {
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

        airlineClassTxt.text = flight.`class`

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = flight.arrivalTime

        val dateFormat = SimpleDateFormat(
            "hh:mm a"
        )

        arrivalTimeTxt.text =
            DateFormat.getDateInstance().format(Date(flight.arrivalTime)) + "\n" + dateFormat.format(
                calendar.time
            )

        calendar.timeInMillis = flight.departureTime
        depTimeTxt.text =
            DateFormat.getDateInstance().format(Date(flight.departureTime)) + "\n" + dateFormat.format(
                calendar.time
            )

        destinationCodeTxt.text = flight.destinationCode
        originCodeTxt.text = flight.originCode

        //setting up fare RecyclerView
        setFareListRecyclerView(flight, appendix)

    }

    private fun setFareListRecyclerView(
        flight: Flight,
        appendix: Appendix
    ) {
        val adapter = FareListAdapter(viewMvcFactory)
        val sortFareList = sortFareList(flight.fares)

        adapter.fareList = sortFareList
        adapter.appendix = appendix

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL

        fareListView.adapter = adapter
        fareListView.layoutManager = linearLayoutManager
    }

    private fun sortFareList(fareList: List<Fare>): ArrayList<Fare> {
        return ArrayList(fareList.sortedBy { it.fare })
    }

}