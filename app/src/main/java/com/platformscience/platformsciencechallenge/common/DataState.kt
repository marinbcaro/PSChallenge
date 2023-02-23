package com.platformscience.platformsciencechallenge.common

sealed class DataState<T> {
    data class Data<T>(val data: T? = null) : DataState<T>()
    data class Loading<T>(val loadingAnimationState: LoadingAnimationState = LoadingAnimationState.Idle) :
        DataState<T>()

    data class Response<T>(val messageComponent: MessageComponent) : DataState<T>()
}
