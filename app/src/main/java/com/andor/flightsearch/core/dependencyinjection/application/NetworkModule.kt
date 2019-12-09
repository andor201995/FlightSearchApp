package com.andor.flightsearch.core.dependencyinjection.application

import com.andor.flightsearch.BuildConfig
import com.andor.flightsearch.network.flight.FetchFlightListEndPoint
import com.andor.flightsearch.network.flight.FetchFlightListEndPointImpl
import com.andor.flightsearch.network.api.FlightApi
import com.andor.flightsearch.network.response.ResponseHandler
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideFlightApi(retrofit: Retrofit): FlightApi =
        retrofit.create(FlightApi::class.java)

    @Provides
    @Singleton
    fun provideFetchListEndPoint(flightApi: FlightApi, responseHandler: ResponseHandler): FetchFlightListEndPoint {
        return FetchFlightListEndPointImpl(
            flightApi,
            responseHandler
        )
    }

    @Provides
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

}
