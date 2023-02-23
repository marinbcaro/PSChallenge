package com.platformscience.platformsciencechallenge.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.platformscience.platformsciencechallenge.common.LoadingAnimationState

@Composable
fun DefaultScreen(
    loadingAnimationState: LoadingAnimationState = LoadingAnimationState.Idle,
    content: @Composable () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colors.background)
        ) {
            content()
            if (loadingAnimationState is LoadingAnimationState.Loading) {
                LoadingTruckAnimation()
            }
        }
    }
}