package com.andor.flightsearch.screens.flightlist


import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andor.flightsearch.network.response.Status
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.controllers.BaseFragment
import com.andor.flightsearch.screens.common.screennavigator.ScreenNavigator
import com.andor.flightsearch.screens.common.viewmodel.AppState
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import com.andor.flightsearch.screens.flightlist.toolbar.ToolbarMvc
import javax.inject.Inject

class ShowFlightListFragment : BaseFragment(), ToolbarMvc.Listener {

    private lateinit var oldState: AppState

    private lateinit var viewModel: FlightSearchViewModel

    private lateinit var appStateObserver: Observer<AppState>

    private lateinit var showFlightListMvc: ShowFlightListMvc

    private lateinit var toolbarMvc: ToolbarMvc

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presentationComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity().viewModelStore, viewModelFactory).get(
            FlightSearchViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showFlightListMvc = viewMvcFactory.getShowFlightListMvc(container!!)
        // Inflate the layout for this fragment
        return showFlightListMvc.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        appStateObserver = Observer {
            val resource = it.flightDetailsResource
            when (resource.status) {
                is Status.Loading -> {
                    showFlightListMvc.startShimmer()
                }
                is Status.Error -> {
                    showFlightListMvc.stopShimmer()
                    screenNavigator.navigateFromShowFlightListToErrorPage()
                }
                is Status.Success -> {
                    showFlightListMvc.updateFlightList(
                        resource.data!!.flights,
                        resource.data.appendix
                    )
                    if (::oldState.isInitialized && it.sortingType != oldState.sortingType) {
                        showFlightListMvc.scrollListToTop()
                    }
                    showFlightListMvc.stopShimmer()
                }
            }
            oldState = it
        }

        viewModel.getAppStateStream().observe(viewLifecycleOwner, appStateObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        toolbarMvc = viewMvcFactory.getToolBarMvc(inflater, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        toolbarMvc.onOptionItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onSettingOptionSelected() {
        screenNavigator.navigateFromSHowFlightToSettingDialog()
    }
}
