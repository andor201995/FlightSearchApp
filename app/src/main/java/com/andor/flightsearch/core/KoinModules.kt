package com.andor.flightsearch.core

import com.andor.flightsearch.BuildConfig
import com.andor.flightsearch.repo.Repository
import com.andor.flightsearch.repo.RepositoryImpl
import com.andor.flightsearch.repo.api.FlightApi
import com.andor.flightsearch.repo.response.ResponseHandler
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { FlightSearchViewModel(get()) }
    single<Repository> { RepositoryImpl(get(), get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
    factory { ResponseHandler() }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideForecastApi(retrofit: Retrofit): FlightApi =
    retrofit.create(FlightApi::class.java)

