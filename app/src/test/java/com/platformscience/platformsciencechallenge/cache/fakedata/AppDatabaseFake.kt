package com.platformscience.platformsciencechallenge.cache.fakedata

import com.platformscience.platformsciencechallenge.cache.DriverEntity
import com.platformscience.platformsciencechallenge.cache.ShipmentEntity


class AppDatabaseFake {
    val drivers = mutableListOf<DriverEntity>()
    val shipments = mutableListOf<ShipmentEntity>()
}