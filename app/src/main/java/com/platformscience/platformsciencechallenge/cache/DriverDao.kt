package com.platformscience.platformsciencechallenge.cache

import androidx.room.*

@Dao
interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDriver(driver: DriverEntity)

    @Query("DELETE FROM driver")
    suspend fun deleteDrivers()

    @Query("SELECT * from driver JOIN shipment ON driver.name = shipment.driver_name")
    suspend fun getDriverShipments(): Map<DriverEntity, ShipmentEntity>
}