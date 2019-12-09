package com.andor.flightsearch.network.flight.flightSchema

data class Airlines(
    val `6E`: String = "",
    val `9W`: String = "",
    val AI: String = "",
    val G8: String = "",
    val SG: String = ""
) {
    companion object {
        const val airlineCode1 = "6E"
        const val airlineCode2 = "9W"
        const val airlineCode3 = "AI"
        const val airlineCode4 = "G8"
        const val airlineCode5 = "SG"
    }
}