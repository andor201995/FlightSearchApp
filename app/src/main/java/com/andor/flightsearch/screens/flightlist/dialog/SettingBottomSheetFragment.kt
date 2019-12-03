package com.andor.flightsearch.screens.flightlist.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.andor.flightsearch.R
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import com.andor.flightsearch.screens.common.viewmodel.SortingType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_setting_bottom_sheet.*

class SettingBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: FlightSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getAppStateStream().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            setSortingType(it.sortingType)
        })

        btn_cancel.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
        btn_apply.setOnClickListener {
            when (radio_group_sorting.checkedRadioButtonId) {
                R.id.radioButton_sort_by_arr_time -> {
                    viewModel.setSortingType(SortingType.ArrTime)
                }
                R.id.radioButton_sort_by_dept_time -> {
                    viewModel.setSortingType(SortingType.DeptTime)
                }
                R.id.radioButton_sort_by_fare -> {
                    viewModel.setSortingType(SortingType.Fare)
                }
            }
            NavHostFragment.findNavController(this).navigateUp()
        }

    }

    private fun setSortingType(sortingType: SortingType) {
        when (sortingType) {
            SortingType.Fare -> {
                radio_group_sorting.check(R.id.radioButton_sort_by_fare)
            }
            SortingType.ArrTime -> {
                radio_group_sorting.check(R.id.radioButton_sort_by_arr_time)
            }
            SortingType.DeptTime -> {
                radio_group_sorting.check(R.id.radioButton_sort_by_dept_time)
            }
        }
    }

}