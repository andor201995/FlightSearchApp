package com.andor.flightsearch.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.flightsearch.R
import com.andor.flightsearch.core.FlightSearchViewModel
import com.andor.flightsearch.core.invisible
import com.andor.flightsearch.core.visible
import com.andor.flightsearch.model.AppState
import com.andor.flightsearch.view.adapter.FlightListAdapter
import com.andor.weatherapp.repo.Status
import kotlinx.android.synthetic.main.fragment_show_flight_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class ShowFlightListFragment : Fragment() {

    private lateinit var oldState: AppState
    private val viewModel: FlightSearchViewModel by sharedViewModel()
    lateinit var adapter: FlightListAdapter

    private lateinit var appStateObserver: Observer<AppState>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_flight_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        appStateObserver = Observer {
            val resource = it.flightDetailsResource
            when (resource.status) {
                is Status.Loading -> {
                    show_list_loader.visible()
                }
                is Status.Error -> {
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_showFlightList_to_errorFragment)
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
                            flight_list.smoothScrollToPosition(0)
                        }
                    }
                    show_list_loader.invisible()
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
