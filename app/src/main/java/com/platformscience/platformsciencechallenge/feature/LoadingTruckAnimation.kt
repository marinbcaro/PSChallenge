package com.platformscience.platformsciencechallenge.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.platformscience.platformsciencechallenge.R

@Composable
fun LoadingTruckAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.moving_truck))
    val progress by animateLottieCompositionAsState(composition)
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}


