package com.platformscience.platformsciencechallenge.feature.drivers

import com.platformscience.platformsciencechallenge.common.LoadingAnimationState
import com.platformscience.platformsciencechallenge.domain.model.DriverShipmentAssignment

data class DriverState(
    val loadingAnimationState: LoadingAnimationState = LoadingAnimationState.Idle,
    val driverShipmentAssignment: List<DriverShipmentAssignment> = listOf(),
    var error: String? = null
)