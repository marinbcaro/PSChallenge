package com.platformscience.platformsciencechallenge.cache.fakedata

import com.platformscience.platformsciencechallenge.cache.ShipmentDao
import com.platformscience.platformsciencechallenge.cache.ShipmentEntity

class ShipmentCacheFake(private val db: AppDatabaseFake) : ShipmentDao {

    override suspend fun getShipmentById(id: Int): ShipmentEntity? {
        return db.shipments.find { it.id == id }
    }

    override suspend fun insertShipment(shipment: ShipmentEntity) {
        if (db.shipments.isNotEmpty()) {
            var didInsert = false
            for (h in db.shipments) {
                if (h.id == shipment.id) {
                    db.shipments.remove(h)
                    db.shipments.add(shipment)
                    didInsert = true
                    break
                }
            }
            if (!didInsert) {
                db.shipments.add(shipment)
            }
        } else {
            db.shipments.add(shipment)
        }
    }

    override suspend fun deleteShipment() {
    }
}