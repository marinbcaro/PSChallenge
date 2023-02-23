package com.platformscience.platformsciencechallenge.datasource

import retrofit2.http.GET
import retrofit2.http.Headers


interface DeliveryService {
    @Headers("mock:true")
    @GET("/drivers_shipments")
    suspend fun getDelivery(): DeliveryResponse
}