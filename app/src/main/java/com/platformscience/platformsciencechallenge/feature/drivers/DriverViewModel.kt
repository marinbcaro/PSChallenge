package com.platformscience.platformsciencechallenge.feature.drivers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platformscience.platformsciencechallenge.common.DataState
import com.platformscience.platformsciencechallenge.datasource.GetDrivers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(private val drivers: GetDrivers) : ViewModel() {
    val state: MutableState<DriverState> = mutableStateOf(DriverState())

    init {
        getDrivers()
    }

    private fun getDrivers() {
        drivers.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    state.value =
                        state.value.copy(loadingAnimationState = dataState.loadingAnimationState)
                }
                is DataState.Data -> {
                    state.value =
                        state.value.copy(driverShipmentAssignment = dataState.data ?: listOf())
                }
                is DataState.Response -> state.value = state.value.copy(error = "error")
            }
        }.launchIn(viewModelScope)
    }
}