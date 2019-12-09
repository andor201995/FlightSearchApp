package com.andor.flightsearch.flight

import com.andor.flightsearch.network.flight.FetchFlightListEndPoint
import com.andor.flightsearch.network.flight.flightSchema.Appendix
import com.andor.flightsearch.network.flight.flightSchema.Flight
import com.andor.flightsearch.network.flight.flightSchema.FlightSchema
import com.andor.flightsearch.network.response.ResponseHandler
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchFlightListUseCaseImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    lateinit var mFetchFlightListEndPoint: FetchFlightListEndPoint
    @Mock
    lateinit var mListener1: FetchFlightListUseCase.Listener
    @Mock
    lateinit var mListener2: FetchFlightListUseCase.Listener
    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: FetchFlightListUseCaseImpl

    @Before
    fun setup() {
        systemUT = FetchFlightListUseCaseImpl(mFetchFlightListEndPoint)
        // initial state setup
        success()
    }

    @Test
    fun fetchQuestionList_success_returnSuccess() {
        // Arrange
        systemUT.registerListener(mListener1)
        systemUT.registerListener(mListener2)
        // Act
        systemUT.fetchQuestionList()
        // Assert
        verify(mFetchFlightListEndPoint).fetchFlightListAndNotify(systemUT)
        verify(mListener1).onFlightDetailsFetched(getFlightDetails())
        verify(mListener2).onFlightDetailsFetched(getFlightDetails())
    }

    @Test
    fun fetchQuestionList_failure_returnFailure() {
        // Arrange
        failure()
        systemUT.registerListener(mListener1)
        systemUT.registerListener(mListener2)
        // Act
        systemUT.fetchQuestionList()
        // Assert
        verify(mFetchFlightListEndPoint).fetchFlightListAndNotify(systemUT)
        verify(mListener1).onFetchFailed()
        verify(mListener2).onFetchFailed()
    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        `when`(mFetchFlightListEndPoint.fetchFlightListAndNotify(any())).thenAnswer {
            val listener = it.getArgument<FetchFlightListEndPoint.Listener>(0)
            listener.handleResponse(ResponseHandler().handleSuccess(getFlightSchema()))
        }
    }

    private fun failure() {
        `when`(mFetchFlightListEndPoint.fetchFlightListAndNotify(any())).thenAnswer {
            val listener = it.getArgument<FetchFlightListEndPoint.Listener>(0)
            listener.handleResponse(ResponseHandler().handleException(Exception("Network error")))
        }
    }

    private fun getFlightSchema(): FlightSchema {
        return FlightSchema(
            Appendix(),
            listOf(
                Flight(
                    destinationCode = "validDestCode",
                    originCode = "validOriginCode",
                    arrivalTime = 100,
                    `class` = "validClass"
                )
            )
        )
    }

    private fun getFlightDetails(): List<FlightDetail> {
        return listOf(
            FlightDetail(
                airlineName = "Unknown",
                destinationCode = "validDestCode",
                originCode = "validOriginCode",
                flightArrivalTimeStamp = 100,
                classOfSeatInFlight = "validClass"
            )
        )
    }

    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}