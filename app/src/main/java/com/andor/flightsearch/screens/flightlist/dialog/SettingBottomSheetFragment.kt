package com.andor.flightsearch.screens.flightlist.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.controllers.BaseBottomSheetDialogFragment
import com.andor.flightsearch.screens.common.screennavigator.ScreenNavigator
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.SortingType
import com.andor.flightsearch.screens.common.viewmodel.ViewModelFactory
import javax.inject.Inject

class SettingBottomSheetFragment : BaseBottomSheetDialogFragment(), SettingBottomSheetMvc.Listener {

    private lateinit var viewMvc: SettingBottomSheetMvc
    private lateinit var viewModel: FlightSearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity().viewModelStore, viewModelFactory).get(
            FlightSearchViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewMvcFactory.getSettingBottomSheetMvc(container)
        // Inflate the layout for this fragment
        return viewMvc.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAppStateStream().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            viewMvc.setCheckBoxType(it.sortingType)
        })
    }

    override fun onApplyButtonClicked(sortingType: SortingType) {
        viewModel.setSortingType(sortingType)
        screenNavigator.navigateUp()
    }

    override fun onCancelButtonClicked() {
        screenNavigator.navigateUp()
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }


}