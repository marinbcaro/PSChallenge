package com.platformscience.platformsciencechallenge.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>) {
    object DriverList : Screen(route = "driverList", arguments = emptyList())
    object ShipmentDetail :
        Screen(route = "shipmentDetail", arguments = listOf(navArgument("driverId") {
            type = NavType.IntType
        }))
}