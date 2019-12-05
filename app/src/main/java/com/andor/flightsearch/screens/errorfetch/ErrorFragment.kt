package com.andor.flightsearch.screens.errorfetch


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andor.flightsearch.network.response.Status
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.controllers.BaseFragment
import com.andor.flightsearch.screens.common.screennavigator.ScreenNavigator
import com.andor.flightsearch.screens.common.viewmodel.AppState
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ErrorFragment : BaseFragment(), ErrorViewMvc.Listener {

    private lateinit var viewMvc: ErrorViewMvc
    private lateinit var viewModel: FlightSearchViewModel
    private lateinit var appStateObserver: Observer<AppState>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)
        viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            viewModelFactory
        ).get(FlightSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewMvcFactory.getErrorMvc(container)
        return viewMvc.rootView
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appStateObserver = Observer {

            when (it.flightDetailsResource.status) {
                is Status.Error -> {
                    viewMvc.displayErrorMessage(it.flightDetailsResource.message)
                }
            }

        }
        viewModel.getAppStateStream().observe(viewLifecycleOwner, appStateObserver)
    }

    override fun onRefreshed() {
        viewMvc.stopRefreshing()
        viewModel.loadFlightDetails()
        screenNavigator.navigateFromErrorToShowFlightListPage()
    }
}
