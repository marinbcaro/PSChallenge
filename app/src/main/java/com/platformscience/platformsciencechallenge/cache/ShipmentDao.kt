package com.platformscience.platformsciencechallenge.cache

import androidx.room.*

@Dao
interface ShipmentDao {
    @Query("SELECT * from shipment where id=:id")
    suspend fun getShipmentById(id: Int): ShipmentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShipment(shipment: ShipmentEntity)

    @Query("DELETE FROM shipment")
    suspend fun deleteShipment()
}