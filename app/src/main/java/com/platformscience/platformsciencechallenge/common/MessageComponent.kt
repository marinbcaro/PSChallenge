package com.platformscience.platformsciencechallenge.common

sealed class MessageComponent {
    data class None(
        val message: String,
    ) : MessageComponent()
}
