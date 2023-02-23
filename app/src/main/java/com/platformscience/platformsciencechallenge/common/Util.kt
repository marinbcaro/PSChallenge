package com.platformscience.platformsciencechallenge.common

import android.content.Context

fun Context.readFileFromAssets(filePath: String): String {
    return resources.assets.open(filePath).bufferedReader().use {
        it.readText()
    }
}