package com.platformscience.platformsciencechallenge.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.platformscience.platformsciencechallenge.domain.DriverDetails
import com.platformscience.platformsciencechallenge.model.Shipment

@Entity(tableName = "shipment")
data class ShipmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    @ColumnInfo(name = "driver_name")
    val driverName: String,
)

fun ShipmentEntity.toShipment() = Shipment(address = this.address, id = this.id)

fun DriverDetails.toShipmentEntity() =
    ShipmentEntity(address = this.assignedDestination, driverName = this.name)