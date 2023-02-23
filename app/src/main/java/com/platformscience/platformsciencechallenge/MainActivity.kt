package com.platformscience.platformsciencechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platformscience.platformsciencechallenge.feature.drivers.DriverList
import com.platformscience.platformsciencechallenge.feature.drivers.DriverViewModel
import com.platformscience.platformsciencechallenge.feature.shipments.ShipmentDetail
import com.platformscience.platformsciencechallenge.feature.shipments.ShipmentViewModel
import com.platformscience.platformsciencechallenge.navigation.Screen
import com.platformscience.platformsciencechallenge.ui.theme.PlatformScienceChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlatformScienceChallengeTheme {
                BoxWithConstraints {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DriverList.route,
                        builder = {
                            addDriverList(navController = navController)
                            addShipmentDetail()
                        })
                }
            }
        }
    }
}

fun NavGraphBuilder.addDriverList(navController: NavController) {
    composable(
        route = Screen.DriverList.route,

        ) {
        val viewModel: DriverViewModel = hiltViewModel()
        DriverList(
            state = viewModel.state.value,
            navigateToDetailScreen = { driverId ->
                navController.navigate("${Screen.ShipmentDetail.route}/$driverId")
            },
        )
    }
}

fun NavGraphBuilder.addShipmentDetail() {
    composable(
        route = Screen.ShipmentDetail.route + "/{driverId}",
        arguments = Screen.ShipmentDetail.arguments
    ) {
        val viewModel: ShipmentViewModel = hiltViewModel()
        ShipmentDetail(state = viewModel.state.value)
    }
}