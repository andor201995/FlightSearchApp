package com.andor.flightsearch.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andor.flightsearch.model.AppState
import com.andor.flightsearch.model.SortingType
import com.andor.flightsearch.model.flightModel.Flight
import com.andor.flightsearch.repo.Repository
import com.andor.weatherapp.repo.Resource
import com.andor.weatherapp.repo.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightSearchViewModel(private val repository: Repository) : ViewModel() {
    private val appStateStream = MutableLiveData<AppState>(AppState())


    fun loadFlightDetails() {
        appStateStream.value = appStateStream.value!!.copy(
            flightDetailsResource = Resource.loading(
                null
            )
        )

        viewModelScope.launch(Dispatchers.IO) {
            val flightDetailResource = repository.getFlightList()
            if (flightDetailResource.status == Status.Success) {
                val sortedFlightList = sortFlightList(
                    flightDetailResource.data!!.flights,
                    appStateStream.value!!.sortingType
                )
                val sortedFlightDetailResource =
                    flightDetailResource.copy(data = flightDetailResource.data.copy(flights = sortedFlightList))
                appStateStream.postValue(appStateStream.value!!.copy(flightDetailsResource = sortedFlightDetailResource))

            } else {
                appStateStream.postValue(appStateStream.value!!.copy(flightDetailsResource = flightDetailResource))
            }
        }
    }

    private fun sortFlightList(
        flights: List<Flight>,
        sortingType: SortingType
    ): List<Flight> {
        return when (sortingType) {
            SortingType.DeptTime -> {
                flights.sortedBy { it.departureTime }
            }
            SortingType.ArrTime -> {
                flights.sortedBy { it.arrivalTime }
            }
            SortingType.Fare -> {
                flights.sortedBy { it.getBestPriceProvider().fare }
            }
        }
    }


    fun getAppStateStream(): LiveData<AppState> {
        return appStateStream
    }

    fun setSortingType(sortingType: SortingType) {
        viewModelScope.launch(Dispatchers.Default) {
            val flightDetailResource = appStateStream.value!!.flightDetailsResource
            if (flightDetailResource.status == Status.Success) {

                val sortedFlightList = sortFlightList(
                    flightDetailResource.data!!.flights,
                    sortingType
                )
                val sortedFlightDetailResource =
                    flightDetailResource.copy(data = flightDetailResource.data.copy(flights = sortedFlightList))

                appStateStream.postValue(
                    appStateStream.value!!.copy(
                        flightDetailsResource = sortedFlightDetailResource,
                        sortingType = sortingType
                    )
                )

            }
        }
    }

}