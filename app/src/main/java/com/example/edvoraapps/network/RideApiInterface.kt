package com.example.edvoraapps.network

import com.example.edvoraapps.data.RidesData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface RideApiInterface {

    @GET("rides")
    suspend fun getRide(): Response<RidesData>
}