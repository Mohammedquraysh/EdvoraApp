package com.example.edvoraapps.repository

import com.example.edvoraapps.data.RidesData
import retrofit2.Response

interface RideRepositoryInterface {
    suspend fun getRide(): Response<RidesData>
}