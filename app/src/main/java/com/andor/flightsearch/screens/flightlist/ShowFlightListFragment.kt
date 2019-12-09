package com.andor.flightsearch.screens.flightlist


import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.controllers.BaseFragment
import com.andor.flightsearch.screens.common.viewmodel.AppState
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import javax.inject.Inject

class ShowFlightListFragment : BaseFragment() {

    private lateinit var viewModel: FlightSearchViewModel

    private lateinit var appStateObserver: Observer<AppState>


    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mShowFlightListController: ShowFlightListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presentationComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity().viewModelStore, mViewModelFactory).get(
            FlightSearchViewModel::class.java
        )
        mShowFlightListController.bindViewModel(viewModel)
        lifecycle.addObserver(mShowFlightListController)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val showFlightListMvc = mViewMvcFactory.getShowFlightListMvc(container)
        mShowFlightListController.bindMvc(showFlightListMvc)
        // Inflate the layout for this fragment
        return showFlightListMvc.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appStateObserver = Observer {
            mShowFlightListController.updateState(it)

        }
        viewModel.getAppStateStream().observe(viewLifecycleOwner, appStateObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        mShowFlightListController.createOptionMenu(inflater, menu)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        mShowFlightListController.unRegisterOptionMenuMvc()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mShowFlightListController.onOptionItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

}
