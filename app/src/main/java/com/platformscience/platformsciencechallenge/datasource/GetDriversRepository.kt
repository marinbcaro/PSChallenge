package com.platformscience.platformsciencechallenge.datasource

import com.platformscience.platformsciencechallenge.cache.DriverDao
import com.platformscience.platformsciencechallenge.cache.DriverEntity
import javax.inject.Inject

class GetDriversRepository @Inject constructor(
    private val driverDao: DriverDao,
    private val service: DeliveryService
) {
    suspend fun insertDriverToCache(driver: DriverEntity) =
        driverDao.insertDriver(driver = driver)

    suspend fun deleteDriversFromCache() =
        driverDao.deleteDrivers()

    suspend fun getDriverShipmentsFromCache() = driverDao.getDriverShipments()

    suspend fun loadShipments(): List<String> {
        val shipments: List<String> = try {
            service.getDelivery().shipments
        } catch (e: Exception) {
            listOf()
        }
        return shipments
    }

    suspend fun loadDrivers(): List<String> {
        val drivers: List<String> = try {
            service.getDelivery().drivers
        } catch (e: Exception) {
            listOf()
        }
        return drivers
    }

}