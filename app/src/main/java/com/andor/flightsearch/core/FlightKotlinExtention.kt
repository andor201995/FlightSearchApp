package com.andor.flightsearch.core

import android.view.View

fun View.invisible() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}