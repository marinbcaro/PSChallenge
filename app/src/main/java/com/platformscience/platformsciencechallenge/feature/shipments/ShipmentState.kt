package com.platformscience.platformsciencechallenge.feature.shipments

import com.platformscience.platformsciencechallenge.common.LoadingAnimationState
import com.platformscience.platformsciencechallenge.domain.model.Shipment

data class ShipmentState(
    val loadingAnimationState: LoadingAnimationState = LoadingAnimationState.Loading,
    val shipment: Shipment? = null,
    var error: String? = null
)