package com.andor.flightsearch.core.dependencyinjection

import androidx.fragment.app.FragmentActivity
import com.andor.flightsearch.BuildConfig
import com.andor.flightsearch.network.Repository
import com.andor.flightsearch.network.RepositoryImpl
import com.andor.flightsearch.network.api.FlightApi
import com.andor.flightsearch.network.response.ResponseHandler
import com.andor.flightsearch.screens.common.viewmodel.FlightSearchViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel {
        FlightSearchViewModel(
            get()
        )
    }
    single<Repository> { RepositoryImpl(get(), get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory {
        provideForecastApi(
            get()
        )
    }
    single { provideRetrofit(get()) }
    factory { ResponseHandler() }
}

val presentationModule = module {

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

