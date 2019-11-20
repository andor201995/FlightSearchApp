package com.andor.flightsearch.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.andor.flightsearch.R
import com.andor.flightsearch.core.FlightSearchViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private val viewModel: FlightSearchViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_main_show_del_mub.setOnClickListener {
            viewModel.loadFlightDetails()
            findNavController(this)
                .navigate(R.id.action_mainFragment_to_showFlightList)
        }
    }
}
