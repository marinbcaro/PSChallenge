package com.platformscience.platformsciencechallenge.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Shipment(val id: Int, val address: String)
