package com.andor.flightsearch.core.dependencyinjection.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.andor.flightsearch.screens.common.ViewMvcFactory
import com.andor.flightsearch.screens.common.screennavigator.ScreenNavigator
import dagger.Module
import dagger.Provides


@Module
class PresentationModule(private val mActivity: FragmentActivity) {

    @Provides
    fun getLayoutInflater(activity: Activity): LayoutInflater {
        return LayoutInflater.from(activity)
    }

    @Provides
    fun getActivity(): Activity {
        return mActivity
    }

    @Provides
    fun context(activity: Activity): Context {
        return activity
    }

    @Provides
    fun provideViewMvcFactory(
        layoutInflater: LayoutInflater
    ): ViewMvcFactory {
        return ViewMvcFactory(layoutInflater)
    }

    @Provides
    fun provideScreenNavigator(activity: Activity): ScreenNavigator {
        return ScreenNavigator(activity)
    }
}
