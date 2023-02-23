package com.platformscience.platformsciencechallenge.datasource

import com.platformscience.platformsciencechallenge.cache.toShipment
import com.platformscience.platformsciencechallenge.common.DataState
import com.platformscience.platformsciencechallenge.common.LoadingAnimationState
import com.platformscience.platformsciencechallenge.common.MessageComponent
import com.platformscience.platformsciencechallenge.domain.model.Shipment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShipments @Inject constructor(private val shipmentsRepository: GetShipmentsRepository) {

    fun execute(id: Int): Flow<DataState<Shipment>> = flow {
        try {
            emit(DataState.Loading(loadingAnimationState = LoadingAnimationState.Loading))
            val shipmentEntity = shipmentsRepository.getShipmentsById(id)
                ?: throw Exception("Shipment does not exist in cache")

            emit(DataState.Data(shipmentEntity.toShipment()))
        } catch (e: Exception) {
            emit(
                DataState.Response(
                    messageComponent = MessageComponent.None(
                        message = "Error",
                    )
                )
            )
        } finally {
            emit(DataState.Loading(LoadingAnimationState.Idle))
        }
    }
}