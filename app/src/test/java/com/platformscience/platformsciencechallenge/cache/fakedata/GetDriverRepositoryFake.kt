package com.platformscience.platformsciencechallenge.cache

import com.platformscience.platformsciencechallenge.datasource.DeliveryResponse
import com.platformscience.platformsciencechallenge.datasource.DeliveryService


val fakeService = object : DeliveryService {
    override suspend fun getDelivery(): DeliveryResponse {
        return DeliveryResponse(drivers = listOf("Carolina"), shipments = listOf("123"))
    }
}