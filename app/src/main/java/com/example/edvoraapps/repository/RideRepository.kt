package com.example.edvoraapps.repository

import com.example.edvoraapps.data.RidesData
import com.example.edvoraapps.network.RideApiInterface
import retrofit2.Response
import javax.inject.Inject


class RideRepository @Inject constructor(private val rideApiInterface: RideApiInterface): RideRepositoryInterface {
    override suspend fun getRide(): Response<RidesData> {
        return rideApiInterface.getRide()
    }
}