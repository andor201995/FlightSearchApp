package com.andor.flightsearch.screens.flightlist


import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.R
import com.andor.flightsearch.network.response.Status
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.controllers.BaseFragment
import com.andor.flightsearch.screens.common.screennavigator.ScreenNavigator
import com.andor.flightsearch.screens.common.viewmodel.AppState
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import com.andor.flightsearch.screens.flightlist.adapter.FlightListAdapter
import kotlinx.android.synthetic.main.fragment_show_flight_list.*
import javax.inject.Inject

class ShowFlightListFragment : BaseFragment() {

    private lateinit var oldState: AppState
    private lateinit var viewModel: FlightSearchViewModel
    lateinit var adapter: FlightListAdapter
    private lateinit var appStateObserver: Observer<AppState>

    private lateinit var showFlightListMvc: ShowFlightListMvc

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
        showFlightListMvc = viewMvcFactory.getShowFlightListMvs(container!!)

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
                    if (flight_list.adapter == null && !(::adapter.isInitialized)) {

                        adapter = FlightListAdapter(context!!)
                        adapter.flightList = ArrayList(resource.data!!.flights)
                        adapter.appendix = resource.data.appendix

                        val linearLayoutManager = LinearLayoutManager(context)
                        linearLayoutManager.orientation = RecyclerView.VERTICAL

                        flight_list.layoutManager = linearLayoutManager
                        flight_list.addItemDecoration(
                            DividerItemDecoration(
                                context,
                                DividerItemDecoration.VERTICAL
                            )
                        )

                        flight_list.adapter = adapter

                    } else {
                        adapter.updateList(resource.data!!.flights)

                        if (::oldState.isInitialized && it.sortingType != oldState.sortingType) {
                            flight_list.layoutManager!!.scrollToPosition(0)
                        }
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
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_setting) {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_showFlightList_to_settingBottomSheetFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}
