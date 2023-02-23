package com.platformscience.platformsciencechallenge

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.platformscience.platformsciencechallenge.domain.model.DriverShipmentAssignment
import com.platformscience.platformsciencechallenge.feature.drivers.DriverList
import com.platformscience.platformsciencechallenge.feature.drivers.DriverState
import com.platformscience.platformsciencechallenge.ui.theme.PlatformScienceChallengeTheme
import org.junit.Rule
import org.junit.Test

class DriverListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun show_drivers_list() {
        composeTestRule.setContent {
            PlatformScienceChallengeTheme {
                val state = remember {
                    DriverState(
                        driverShipmentAssignment = listOf(
                            DriverShipmentAssignment(
                                1,
                                "Carolina",
                                "12855 Runway Rd"
                            )
                        )
                    )
                }
                DriverList(state = state, navigateToDetailScreen = {})
            }
        }
        composeTestRule.onNodeWithText("Carolina").assertIsDisplayed()
    }
}