package com.platformscience.platformsciencechallenge.feature.drivers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.platformscience.platformsciencechallenge.R

@Composable
fun DriverItem(driver: String, driverId: Int, onSelectedDriver: (Int) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectedDriver(driverId) }
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colors.surface), elevation = 8.dp

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(70.dp)
                    .padding(2.dp),
                painter = painterResource(id = R.drawable.driver),
                contentDescription = stringResource(id = R.string.driver_image)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = driver,
                    style = MaterialTheme.typography.h6
                )
            }

        }

    }
}