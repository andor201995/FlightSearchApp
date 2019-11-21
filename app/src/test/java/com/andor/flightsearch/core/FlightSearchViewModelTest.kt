package com.andor.flightsearch.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andor.flightsearch.model.AppState
import com.andor.flightsearch.model.SortingType
import com.andor.flightsearch.model.flightModel.FlightDetails
import com.andor.flightsearch.repo.Repository
import com.andor.flightsearch.repo.response.Resource
import com.andor.flightsearch.repo.response.Status
import com.andor.flightsearch.util.getOrAwaitValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FlightSearchViewModelTest {

    private lateinit var viewModel: FlightSearchViewModel
    private lateinit var flightRepo: Repository
    private lateinit var weatherObserver: Observer<AppState>

    private val successResource = Resource.success(FlightDetails())

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setupBefore() {
        flightRepo = mock()
        runBlocking {
            whenever(flightRepo.getFlightList()).thenReturn(successResource)
        }

        viewModel = FlightSearchViewModel(flightRepo)
        weatherObserver = mock()
        viewModel.getAppStateStream().observeForever(weatherObserver)
    }

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun loadFlightDetails() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()
        viewModel.loadFlightDetails(dispatcher = testCoroutineDispatcher)
        val beforeCoroutineValue = viewModel.getAppStateStream().getOrAwaitValue()
        assertEquals(Status.Loading, beforeCoroutineValue.flightDetailsResource.status)
        testCoroutineDispatcher.resumeDispatcher()
        val afterCoroutineValue = viewModel.getAppStateStream().getOrAwaitValue()
        assertEquals(Status.Success, afterCoroutineValue.flightDetailsResource.status)
    }


    @Test
    fun setSortingTypeDeptTime() = testCoroutineDispatcher.runBlockingTest {

        loadData()

        testCoroutineDispatcher.pauseDispatcher()
        viewModel.setSortingType(SortingType.DeptTime, testCoroutineDispatcher)
        testCoroutineDispatcher.resumeDispatcher()
        val sortingTypeValue = viewModel.getAppStateStream().value!!.sortingType
        assertEquals(SortingType.DeptTime, sortingTypeValue)
    }

    @Test
    fun setSortingTypeArrTime() = testCoroutineDispatcher.runBlockingTest {
        loadData()

        testCoroutineDispatcher.pauseDispatcher()
        viewModel.setSortingType(SortingType.ArrTime, testCoroutineDispatcher)
        testCoroutineDispatcher.resumeDispatcher()
        val sortingTypeValue = viewModel.getAppStateStream().value!!.sortingType
        assertEquals(SortingType.ArrTime, sortingTypeValue)

    }

    @Test
    fun setSortingTypeFare() = testCoroutineDispatcher.runBlockingTest {
        loadData()

        testCoroutineDispatcher.pauseDispatcher()
        viewModel.setSortingType(SortingType.Fare, testCoroutineDispatcher)
        testCoroutineDispatcher.resumeDispatcher()
        val sortingTypeValue = viewModel.getAppStateStream().value!!.sortingType
        assertEquals(SortingType.Fare, sortingTypeValue)
    }

    private fun loadData() {
        viewModel.loadFlightDetails(dispatcher = testCoroutineDispatcher)
    }
}