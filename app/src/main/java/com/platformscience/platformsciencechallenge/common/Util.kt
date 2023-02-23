package com.platformscience.platformsciencechallenge.common

import android.content.Context
import com.platformscience.platformsciencechallenge.cache.DriverEntity
import com.platformscience.platformsciencechallenge.model.Driver

fun Context.readFileFromAssets(filePath: String): String {
    return resources.assets.open(filePath).bufferedReader().use {
        it.readText()
    }
}

fun String.toDriver(): Driver = Driver(id = 0, name = this)

fun String.toDriverEntity(): DriverEntity = DriverEntity(name = this)