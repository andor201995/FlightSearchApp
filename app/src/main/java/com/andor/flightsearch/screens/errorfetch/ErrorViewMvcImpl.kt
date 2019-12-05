package com.andor.flightsearch.screens.errorfetch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.andor.flightsearch.R
import com.andor.flightsearch.screens.common.views.BaseObservableViewMvc

class ErrorViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<ErrorViewMvc.Listener>(), ErrorViewMvc {


    private var swipeRefreshLayout: SwipeRefreshLayout
    private var errorMessageTxt: TextView

    init {
        rootView = inflater.inflate(R.layout.fragment_error, parent, false)
        errorMessageTxt = findViewById(R.id.error_txt)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh)

        swipeRefreshLayout.setOnRefreshListener {
           for(listener in listeners){
               listener.onRefreshed()
           }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun displayErrorMessage(message: String?) {
        errorMessageTxt.text = "Error Message =${message}"
    }

    override fun stopRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }
}