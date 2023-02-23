package com.platformscience.platformsciencechallenge.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.platformscience.platformsciencechallenge.domain.DriverDetails

@Entity(tableName = "driver")
data class DriverEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)

fun DriverDetails.toDriverEntity(): DriverEntity = DriverEntity(name = this.name)
