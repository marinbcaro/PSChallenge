package com.platformscience.platformsciencechallenge.cache

import com.platformscience.platformsciencechallenge.cache.fakedata.AppDatabaseFake
import com.platformscience.platformsciencechallenge.cache.fakedata.ShipmentCacheFake
import com.platformscience.platformsciencechallenge.common.DataState
import com.platformscience.platformsciencechallenge.common.LoadingAnimationState
import com.platformscience.platformsciencechallenge.common.MessageComponent
import com.platformscience.platformsciencechallenge.datasource.GetShipments
import com.platformscience.platformsciencechallenge.datasource.GetShipmentsRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetShipmentTest {
    private lateinit var getShipments: GetShipments


    @Test
    fun `Verify GetShipments UseCase emission failure`() = runBlocking {
        val db = AppDatabaseFake()
        val shipmentCache = ShipmentCacheFake(db)
        val repoShipment = GetShipmentsRepository(shipmentDao = shipmentCache)

        repoShipment.insertShipment(ShipmentEntity(1, "123", "Carolina"))
        getShipments = GetShipments(repoShipment)


        val emissions = getShipments.execute(0).toList()


        assert(emissions[0] == DataState.Loading<DriverEntity>(LoadingAnimationState.Loading))

        assert(emissions[1] is DataState.Response)
        assert(((emissions[1] as DataState.Response).messageComponent as MessageComponent.None).message == "Error")
        assert(emissions[2] == DataState.Loading<DriverEntity>(LoadingAnimationState.Idle))
    }

    @Test
    fun `Verify GetShipments UseCase emission success`() = runBlocking {
        val db = AppDatabaseFake()
        val shipmentCache = ShipmentCacheFake(db)
        val repoShipment = GetShipmentsRepository(shipmentDao = shipmentCache)

        val shipmentEntity = ShipmentEntity(1, "123", "Carolina")
        repoShipment.insertShipment(shipmentEntity)
        getShipments = GetShipments(repoShipment)


        val emissions = getShipments.execute(1).toList()
        
        assert(emissions[0] == DataState.Loading<DriverEntity>(LoadingAnimationState.Loading))

        assert(emissions[1] is DataState.Data)
        assert(((emissions[1] as DataState.Data).data?.address == shipmentEntity.address))
        assert(emissions[2] == DataState.Loading<DriverEntity>(LoadingAnimationState.Idle))
    }

}