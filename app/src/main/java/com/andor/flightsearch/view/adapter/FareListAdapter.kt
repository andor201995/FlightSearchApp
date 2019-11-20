package com.andor.flightsearch.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.R
import com.andor.flightsearch.model.flightModel.Appendix
import com.andor.flightsearch.model.flightModel.Fare
import com.andor.flightsearch.model.flightModel.Providers

class FareListAdapter : RecyclerView.Adapter<FareListAdapter.FareHolder>() {
    lateinit var appendix: Appendix
    lateinit var fareList: ArrayList<Fare>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FareHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fare_item_view, parent, false)
        return FareHolder(itemView)
    }

    override fun getItemCount(): Int {
        return fareList.size
    }

    override fun onBindViewHolder(holder: FareHolder, position: Int) {
        holder.fareProviderTxt.text = when (fareList[position].providerId) {
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
        holder.fareTxt.text = fareList[position].fare.toString()
    }

    inner class FareHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fareProviderTxt: TextView = view.findViewById(R.id.txt_fare_provider)
        val fareTxt: TextView = view.findViewById(R.id.txt_fare)
    }
}
