package com.platformscience.platformsciencechallenge.feature.shipments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.platformscience.platformsciencechallenge.R
import com.platformscience.platformsciencechallenge.feature.DefaultScreen

@Composable
fun ShipmentDetail(state: ShipmentState) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.delivery_map))
    val progress by animateLottieCompositionAsState(composition)

    DefaultScreen(loadingAnimationState = state.loadingAnimationState) {
        state.shipment?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(12.dp),
                    text = stringResource(id = R.string.destination_label),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 20.dp),
                    text = it.address,
                    style = MaterialTheme.typography.h6

                )
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxSize(),
                    composition = composition,
                    progress = { progress },
                )
            }
        }
    }
}