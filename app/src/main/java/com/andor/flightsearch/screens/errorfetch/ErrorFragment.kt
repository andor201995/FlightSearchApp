package com.andor.flightsearch.screens.errorfetch


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.andor.flightsearch.R
import com.andor.flightsearch.network.response.Status
import com.andor.flightsearch.screens.common.controllers.BaseFragment
import com.andor.flightsearch.screens.common.viewmodel.AppState
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_error.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ErrorFragment : BaseFragment() {

    private lateinit var viewModel: FlightSearchViewModel
    private lateinit var appStateObserver: Observer<AppState>
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presentationComponent.inject(this)

        viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            viewModelFactory
        ).get(FlightSearchViewModel::class.java)

        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
