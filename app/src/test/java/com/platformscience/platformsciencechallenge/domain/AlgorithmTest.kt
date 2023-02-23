package com.platformscience.platformsciencechallenge.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test


class AlgorithmTest {


    private val subject = Algorithm()

    @Test
    fun `Correct score is assigned based on driver's name`() {

        val driverDetails = subject.driverDetails
        val drivers = listOf("Carolina Hessen", "Sophia Hessen")
        val shipments = listOf("12855 Runway Rd", "12721 Millennium Drive")

        subject.populateDriverDetails(drivers)
        subject.calculateScores(shipments)


        assertEquals(driverDetails["Carolina Hessen"]?.consonantsScore, 8f)
        assertEquals(driverDetails["Carolina Hessen"]?.vowelScore, 9f)
        assertEquals(driverDetails["Carolina Hessen"]?.assignedDestination, "12855 Runway Rd")
    }

}