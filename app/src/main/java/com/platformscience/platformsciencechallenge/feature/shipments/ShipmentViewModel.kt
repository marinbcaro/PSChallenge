package com.platformscience.platformsciencechallenge.feature.shipments

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platformscience.platformsciencechallenge.common.DataState
import com.platformscience.platformsciencechallenge.datasource.GetShipments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShipmentViewModel @Inject constructor(
    private val shipments: GetShipments,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: MutableState<ShipmentState> = mutableStateOf(ShipmentState())

    init {
        savedStateHandle.get<Int>("driverId")?.let { driverId ->
            getShipment(driverId)
        }
    }

    private fun getShipment(id: Int) {

        shipments.execute(id).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> state.value =
                    state.value.copy(loadingAnimationState = dataState.loadingAnimationState)
                is DataState.Data -> state.value =
                    state.value.copy(shipment = dataState.data)
                is DataState.Response -> state.value = state.value.copy(error = "error")
            }
        }.launchIn(viewModelScope)
    }
}