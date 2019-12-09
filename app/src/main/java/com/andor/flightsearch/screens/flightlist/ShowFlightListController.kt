package com.andor.flightsearch.screens.flightlist

import android.app.Activity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.LifecycleObserver
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.screennavigator.ScreenNavigator
import com.andor.flightsearch.screens.common.viewmodel.AppState
import com.andor.flightsearch.screens.common.viewmodel.FlightResponseType
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.flightlist.toolbar.ToolbarMvc

class ShowFlightListController(
    private val mScreenNavigator: ScreenNavigator,
    private val mActivity: Activity,
    private val mViewMvcFactory: ViewMvcFactory

) : LifecycleObserver, ToolbarMvc.Listener {

    private lateinit var mToolbarMvc: ToolbarMvc
    private lateinit var mViewModel: FlightSearchViewModel
    private lateinit var oldState: AppState
    private lateinit var mViewMvc: ShowFlightListMvc

    fun updateState(it: AppState) {
        when (val response = it.flightResponseType) {
            is FlightResponseType.Loading -> {
                mViewMvc.startShimmer()
            }
            is FlightResponseType.Failure -> {
                mViewMvc.stopShimmer()
                mScreenNavigator.navigateFromShowFlightListToErrorPage()
            }
            is FlightResponseType.Success -> {
                mViewMvc.updateFlightList(
                    response.flightDetailList
                )
                if (::oldState.isInitialized && it.sortingType != oldState.sortingType) {
                    mViewMvc.scrollListToTop()
                }
                mViewMvc.stopShimmer()

                mActivity.invalidateOptionsMenu()
            }
        }
        oldState = it
    }

    fun bindMvc(showFlightListMvc: ShowFlightListMvc) {
        this.mViewMvc = showFlightListMvc
    }

    fun bindViewModel(viewModel: FlightSearchViewModel) {
        this.mViewModel = viewModel
    }

    fun createOptionMenu(inflater: MenuInflater, menu: Menu) {
        if (mViewModel.getAppStateStream().value!!.flightResponseType is FlightResponseType.Success) {
            mToolbarMvc = mViewMvcFactory.getToolBarMvc(inflater, menu)
            mToolbarMvc.registerListener(this)
        }
    }

    fun unRegisterOptionMenuMvc() {
        if (::mToolbarMvc.isInitialized) {
            mToolbarMvc.unregisterListener(this)
        }
    }

    override fun onSettingOptionSelected() {
        mScreenNavigator.navigateFromSHowFlightToSettingDialog()
    }

    fun onOptionItemSelected(item: MenuItem) {
        mToolbarMvc.onOptionItemSelected(item)
    }
}