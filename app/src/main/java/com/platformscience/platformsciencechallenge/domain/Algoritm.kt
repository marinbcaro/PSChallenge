package com.platformscience.platformsciencechallenge.domain

import java.util.*

class DriverSuitabilityComparator : Comparator<DriverSuitability> {

    //Comparator used to sort the tree by value
    override fun compare(d1: DriverSuitability, d2: DriverSuitability): Int {
        val scoreResult = d1.score.compareTo(d2.score)
        return if (scoreResult == 0) {
            d1.name.compareTo(d2.name)
        } else scoreResult
    }
}

class Algorithm {
    var driverDetails: HashMap<String, DriverDetails> = HashMap()

    private fun isVowel(input: Char): Boolean {
        return when (input.lowercaseChar()) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }
    }

    //Check for common factors
    private fun shareFactor(shipment: String, driver: String): Boolean {
        val sLen: Int = shipment.length
        val dLen: Int = driver.length
        val min = if (sLen <= dLen) sLen else dLen
        for (i in 1 until min / 2) {
            if (sLen % i == 0 && dLen % i == 0) {
                return true
            }
        }
        return false
    }


    fun populateDriverDetails(drivers: List<String>) {
        for (driver in drivers) {
            val details = DriverDetails()
            details.name = driver

            // count vowels and consonants in name
            var vowels = 0.0f
            var consonants = 0.0f
            for (element in driver) {
                if (element.isLetter()) {
                    if (isVowel(element)) {
                        ++vowels
                    } else {
                        ++consonants
                    }
                }
            }
            details.vowelScore = vowels * 1.5f
            details.consonantsScore = consonants

            driverDetails[driver] = details
        }
    }

    fun calculateScores(shipments: List<String>) {
        for (shipment in shipments) {
            val suitabilitySet: TreeSet<DriverSuitability> =
                TreeSet<DriverSuitability>(DriverSuitabilityComparator())
            for (driverDetails in driverDetails.values) {
                if (driverDetails.assignedDestination.isNotEmpty()) {
                    continue
                }
                var score = 0.0f
                // even
                if (shipment.length % 2 == 0) {
                    score = driverDetails.vowelScore
                    // find common factors
                    if (shareFactor(shipment, driverDetails.name)) {
                        score *= 1.5f
                    }
                } // odd
                else {
                    score = driverDetails.consonantsScore
                    if (shareFactor(shipment, driverDetails.name)) {
                        score *= 1.5f
                    }
                }
                val suitability = DriverSuitability()
                suitability.name = driverDetails.name
                suitability.score = score
                suitabilitySet.add(suitability)
            }

            assignStreetBasedOnHighestScore(suitabilitySet, shipment)
        }
    }

    // Get highest score and assign street
    private fun assignStreetBasedOnHighestScore(
        suitabilitySet: TreeSet<DriverSuitability>,
        shipment: String
    ) {
        val highest = suitabilitySet.last()
        val highestDetails = driverDetails[highest.name]
        highestDetails?.let {
            it.assignedDestination = shipment
            driverDetails[it.name] = highestDetails
        }
    }
}

