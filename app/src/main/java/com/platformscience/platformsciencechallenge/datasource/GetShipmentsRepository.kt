package com.platformscience.platformsciencechallenge.datasource

import com.platformscience.platformsciencechallenge.cache.ShipmentDao
import com.platformscience.platformsciencechallenge.cache.ShipmentEntity
import javax.inject.Inject

class GetShipmentsRepository @Inject constructor(private val shipmentDao: ShipmentDao) {
    suspend fun insertShipment(shipments: ShipmentEntity) =
        shipmentDao.insertShipment(shipments)

    suspend fun deleteShipments() =
        shipmentDao.deleteShipment()

    suspend fun getShipmentsById(id: Int) = shipmentDao.getShipmentById(id)
}