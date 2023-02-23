package com.platformscience.platformsciencechallenge.datasource

import javax.inject.Inject

class DeliveryRepository @Inject constructor(private val service: DeliveryService) :
    DeliveryService {
    override suspend fun getDelivery(): DeliveryResponse = service.getDelivery()
}