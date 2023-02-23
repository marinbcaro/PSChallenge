package com.platformscience.platformsciencechallenge.datasource

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryResponse(val shipments: List<String>, val drivers: List<String>)