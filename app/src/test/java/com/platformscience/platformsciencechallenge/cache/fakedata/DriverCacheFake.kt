package com.platformscience.platformsciencechallenge.cache.fakedata

import com.platformscience.platformsciencechallenge.cache.DriverDao
import com.platformscience.platformsciencechallenge.cache.DriverEntity
import com.platformscience.platformsciencechallenge.cache.ShipmentEntity

class DriverCacheFake(private val db: AppDatabaseFake) : DriverDao {

    override suspend fun insertDriver(driver: DriverEntity) {
        if (db.drivers.isNotEmpty()) {
            var didInsert = false
            for (h in db.drivers) {
                if (h.id == driver.id) {
                    db.drivers.remove(h)
                    db.drivers.add(driver)
                    didInsert = true
                    break
                }
            }
            if (!didInsert) {
                db.drivers.add(driver)
            }
        } else {
            db.drivers.add(driver)
        }
    }

    override suspend fun deleteDrivers() {
    }

    override suspend fun getDriverShipments(): Map<DriverEntity, ShipmentEntity> {
        val map = HashMap<DriverEntity, ShipmentEntity>()
        map[db.drivers[0]] = db.shipments[0]
        return map
    }
}