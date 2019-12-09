package com.andor.flightsearch.screens.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andor.flightsearch.flight.FetchFlightListUseCase
import com.andor.flightsearch.flight.FlightDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightSearchViewModel(private val useCase: FetchFlightListUseCase) :
    ViewModel(), FetchFlightListUseCase.Listener {

    private val appStateStream = MutableLiveData<AppState>(
        AppState()
    )

    init {
        useCase.registerListener(this)
    }

    override fun onCleared() {
        super.onCleared()
        useCase.unregisterListener(this)
    }

    fun loadFlightDetails() {
        appStateStream.value = appStateStream.value!!.copy(
            flightResponseType = FlightResponseType.Loading
        )
        useCase.fetchQuestionList()
    }

    private fun sortFlightList(
        flights: List<FlightDetail>,
        sortingType: SortingType
    ): List<FlightDetail> {
        return when (sortingType) {
            SortingType.DeptTime -> {
                flights.sortedBy { it.flightDepartureTimeStamp }
            }
            SortingType.ArrTime -> {
                flights.sortedBy { it.flightArrivalTimeStamp }
            }
            SortingType.Fare -> {
                flights.sortedBy { it.getBestPriceProvider().costOfFlight }
            }
        }
    }


    fun getAppStateStream(): LiveData<AppState> {
        return appStateStream
    }

    fun setSortingType(
        sortingType: SortingType,
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ) {
        viewModelScope.launch(dispatcher) {
            val flightResponseType = appStateStream.value!!.flightResponseType
            if (flightResponseType is FlightResponseType.Success) {

                val sortedFlightList = sortFlightList(
                    flightResponseType.flightDetailList,
                    sortingType
                )
                appStateStream.postValue(
                    appStateStream.value!!.copy(
                        flightResponseType = flightResponseType.copy(flightDetailList = sortedFlightList),
                        sortingType = sortingType
                    )
                )

            }
        }
    }

    override fun onFlightDetailsFetched(flightDetailList: List<FlightDetail>) {
        val sortedFlightList = sortFlightList(
            flightDetailList,
            appStateStream.value!!.sortingType
        )
        appStateStream.postValue(
            appStateStream.value!!.copy(
                flightResponseType = FlightResponseType.Success(
                    sortedFlightList
                )
            )
        )
    }

    override fun onFetchFailed() {
        appStateStream.postValue(
            appStateStream.value!!.copy(
                flightResponseType = FlightResponseType.Failure
            )
        )
    }

}