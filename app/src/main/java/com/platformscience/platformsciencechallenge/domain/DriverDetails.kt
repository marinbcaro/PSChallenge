package com.platformscience.platformsciencechallenge.domain


data class DriverDetails(
    var name: String = "",
    var vowelScore: Float = 0f,
    var consonantsScore: Float = 0f,
    var assignedDestination: String = ""
)
