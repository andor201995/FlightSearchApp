package com.andor.flightsearch.screens.flightlist.dialog

import com.andor.flightsearch.screens.common.viewmodel.SortingType
import com.andor.flightsearch.screens.common.views.ObservableViewMvc

interface SettingBottomSheetMvc : ObservableViewMvc<SettingBottomSheetMvc.Listener> {
    interface Listener {
        fun onApplyButtonClicked(sortingType: SortingType)
        fun onCancelButtonClicked()
    }

    fun setCheckBoxType(sortingType: SortingType)
}