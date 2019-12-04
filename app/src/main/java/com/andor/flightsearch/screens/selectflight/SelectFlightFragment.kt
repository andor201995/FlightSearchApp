package com.andor.flightsearch.screens.selectflight


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.controllers.BaseFragment
import com.andor.flightsearch.screens.common.screennavigator.ScreenNavigator
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SelectFlightFragment : BaseFragment(), SelectFlightViewMvc.Listener {

    private lateinit var viewModel: FlightSearchViewModel
    private lateinit var selectFlightViewMvc: SelectFlightViewMvc

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
        // Inflate the layout for this fragment
        selectFlightViewMvc = viewMvcFactory.getSelectFlightViewMvc(container)

        return selectFlightViewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        selectFlightViewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        selectFlightViewMvc.unregisterListener(this)
    }

    override fun onButtonClicked() {
        viewModel.loadFlightDetails()
        screenNavigator.navigateFromSelectedFlightToShowFlight()

    }
}
