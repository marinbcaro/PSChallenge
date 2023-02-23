package com.platformscience.platformsciencechallenge.cache

import com.platformscience.platformsciencechallenge.cache.fakedata.AppDatabaseFake
import com.platformscience.platformsciencechallenge.cache.fakedata.DriverCacheFake
import com.platformscience.platformsciencechallenge.cache.fakedata.ShipmentCacheFake
import com.platformscience.platformsciencechallenge.common.DataState
import com.platformscience.platformsciencechallenge.common.LoadingAnimationState
import com.platformscience.platformsciencechallenge.datasource.GetDrivers
import com.platformscience.platformsciencechallenge.datasource.GetDriversRepository
import com.platformscience.platformsciencechallenge.datasource.GetShipmentsRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetDriverTest {

    private lateinit var getDrivers: GetDrivers


    @Test
    fun `Verify GetDrivers UseCase emission success`() = runBlocking {
        val db = AppDatabaseFake()
        val driverCache = DriverCacheFake(db)
        val shipmentCache = ShipmentCacheFake(db)
        val repoDriver = GetDriversRepository(service = fakeService, driverDao = driverCache)
        val repoShipment = GetShipmentsRepository(shipmentDao = shipmentCache)

        val driver = DriverEntity(id = 1, name = "Carolina")

        driverCache.insertDriver(driver)

        val shipment = ShipmentEntity(id = 1, address = "123", driverName = "Carolina")

        shipmentCache.insertShipment(shipment)

        getDrivers = GetDrivers(driversRepository = repoDriver, shipmentRepository = repoShipment)


        val emissions = getDrivers.execute().toList()


        assert(emissions[0] == DataState.Loading<DriverEntity>(LoadingAnimationState.Loading))

        assert(emissions[1] is DataState.Data)
        assert((emissions[1] as DataState.Data).data?.get(0)?.driver == driver.name)
        assert(emissions[2] == DataState.Loading<DriverEntity>(LoadingAnimationState.Idle))
    }

}