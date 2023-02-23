package com.platformscience.platformsciencechallenge.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DriverEntity::class, ShipmentEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDriverDao(): DriverDao
    abstract fun getShipmentDao(): ShipmentDao

    companion object {
        val DATABASE_NAME: String = "ps_db"
    }
}