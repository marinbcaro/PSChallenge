package com.platformscience.platformsciencechallenge.model

import kotlinx.serialization.Serializable

@Serializable
data class Shipment(val id: Int, val address: String)
