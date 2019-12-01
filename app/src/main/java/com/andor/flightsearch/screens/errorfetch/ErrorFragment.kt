package com.andor.flightsearch.screens.errorfetch


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.andor.flightsearch.R
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.AppState
import com.andor.flightsearch.network.response.Status
import kotlinx.android.synthetic.main.fragment_error.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class ErrorFragment : Fragment() {

    private val viewModel: FlightSearchViewModel by sharedViewModel()
    private lateinit var appStateObserver: Observer<AppState>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appStateObserver = Observer {
            when (it.flightDetailsResource.status) {
                is Status.Error -> {
                    error_txt.text = "Error Message =${it.flightDetailsResource.message}"
                }
            }
        }
        viewModel.getAppStateStream().observe(viewLifecycleOwner, appStateObserver)
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            viewModel.loadFlightDetails()
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_errorFragment_to_showFlightList)
        }
    }
}
