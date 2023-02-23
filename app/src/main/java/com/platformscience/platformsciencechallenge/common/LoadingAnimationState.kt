package com.platformscience.platformsciencechallenge.common

sealed class LoadingAnimationState {
    object Loading : LoadingAnimationState()
    object Idle : LoadingAnimationState()
}