package com.andor.flightsearch.screens.flightlist.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import com.andor.flightsearch.R
import com.andor.flightsearch.screens.common.viewmodel.SortingType
import com.andor.flightsearch.screens.common.views.BaseObservableViewMvc

class SettingBottomSheetMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    BaseObservableViewMvc<SettingBottomSheetMvc.Listener>(),
    SettingBottomSheetMvc {

    private var sortingRadioGroup: RadioGroup

    init {
        rootView = inflater.inflate(R.layout.fragment_setting_bottom_sheet, parent, false)
        sortingRadioGroup = findViewById(R.id.radio_group_sorting)

        setButtonListener()
    }

    private fun setButtonListener() {
        val cancelButton = findViewById<Button>(R.id.btn_cancel)
        val applyButton = findViewById<Button>(R.id.btn_apply)

        cancelButton.setOnClickListener {
            for (listener in listeners) {
                listener.onCancelButtonClicked()
            }

        }
        listeners
        applyButton.setOnClickListener {
            when (sortingRadioGroup.checkedRadioButtonId) {
                R.id.radioButton_sort_by_arr_time -> {
                    for (listener in listeners) {
                        listener.onApplyButtonClicked(SortingType.ArrTime)
                    }
                }
                R.id.radioButton_sort_by_dept_time -> {
                    for (listener in listeners) {
                        listener.onApplyButtonClicked(SortingType.DeptTime)
                    }
                }
                R.id.radioButton_sort_by_fare -> {
                    for (listener in listeners) {
                        listener.onApplyButtonClicked(SortingType.Fare)
                    }
                }
            }
        }
    }

    override fun setCheckBoxType(sortingType: SortingType) {
        when (sortingType) {
            SortingType.Fare -> {
                sortingRadioGroup.check(R.id.radioButton_sort_by_fare)
            }
            SortingType.ArrTime -> {
                sortingRadioGroup.check(R.id.radioButton_sort_by_arr_time)
            }
            SortingType.DeptTime -> {
                sortingRadioGroup.check(R.id.radioButton_sort_by_dept_time)
            }
        }
    }


}