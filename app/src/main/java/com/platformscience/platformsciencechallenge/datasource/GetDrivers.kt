package com.platformscience.platformsciencechallenge.datasource

import com.platformscience.platformsciencechallenge.cache.toDriverEntity
import com.platformscience.platformsciencechallenge.cache.toShipmentEntity
import com.platformscience.platformsciencechallenge.common.DataState
import com.platformscience.platformsciencechallenge.common.LoadingAnimationState
import com.platformscience.platformsciencechallenge.common.MessageComponent
import com.platformscience.platformsciencechallenge.domain.Algorithm
import com.platformscience.platformsciencechallenge.model.DriverShipmentAssignment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDrivers(
    private val driversRepository: GetDriversRepository,
    private val shipmentRepository: GetShipmentsRepository,
) {

    fun execute(): Flow<DataState<List<DriverShipmentAssignment>>> = flow {
        try {
            emit(DataState.Loading(loadingAnimationState = LoadingAnimationState.Loading))

            val drivers: List<String> = driversRepository.loadDrivers()
            val shipments: List<String> = driversRepository.loadShipments()

            clearDatabase()

            val algorithm = Algorithm()
            algorithm.populateDriverDetails(drivers)
            algorithm.calculateScores(shipments)

            insertDriverAssignmentToCache(algorithm)

            val driversShipments = getDriverShipment()

            emit(DataState.Data(driversShipments))
        } catch (e: Exception) {
            emit(
                DataState.Response(
                    messageComponent = MessageComponent.None(
                        message = "Error",
                    )
                )
            )
        } finally {
            emit(DataState.Loading(loadingAnimationState = LoadingAnimationState.Idle))
        }
    }

    private suspend fun insertDriverAssignmentToCache(algorithm: Algorithm) {
        algorithm.driverDetails.values.mapIndexed { _, driverDetail ->

            driversRepository.insertDriverToCache(driverDetail.toDriverEntity())
            shipmentRepository.insertShipment(
                driverDetail.toShipmentEntity()
            )
        }
    }

    private suspend fun getDriverShipment(): List<DriverShipmentAssignment> {
        val driversShipments = driversRepository.getDriverShipmentsFromCache().map {
            val id = it.key.id
            DriverShipmentAssignment(id = id, driver = it.key.name, shipment = it.value.address)
        }
        return driversShipments
    }

    private suspend fun clearDatabase() {
        driversRepository.deleteDriversFromCache()
        shipmentRepository.deleteShipments()
    }
}