package com.andor.flightsearch.screens.selectflight

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import com.andor.flightsearch.R
import com.andor.flightsearch.screens.common.views.BaseObservableViewMvc

class SelectFlightViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<SelectFlightViewMvc.Listener>(), SelectFlightViewMvc {

    init {
        rootView = inflater.inflate(R.layout.fragment_select_flight, parent, false)
        val delMumbaiButton = findViewById<Button>(R.id.btn_main_show_del_mub)

        delMumbaiButton.setOnClickListener {
            for (listener in listeners) {
                listener.onButtonClicked()
            }
        }
    }


}