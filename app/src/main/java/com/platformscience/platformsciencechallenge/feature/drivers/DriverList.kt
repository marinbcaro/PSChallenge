package com.platformscience.platformsciencechallenge.feature.drivers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.platformscience.platformsciencechallenge.feature.DefaultScreen

@Composable
fun DriverList(state: DriverState, navigateToDetailScreen: (Int) -> Unit) {

    DefaultScreen(loadingAnimationState = state.loadingAnimationState) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {

            items(state.driverShipmentAssignment) { assigment ->
                DriverItem(
                    driver = assigment.driver,
                    driverId = assigment.id,
                    onSelectedDriver = navigateToDetailScreen
                )
            }
        }
    }
}