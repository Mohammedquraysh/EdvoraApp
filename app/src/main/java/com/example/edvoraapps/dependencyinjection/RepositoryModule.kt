package com.example.edvoraapps.dependencyinjection

import com.example.edvoraapps.network.RideApiInterface
import com.example.edvoraapps.repository.RideRepository
import com.example.edvoraapps.repository.RideRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRideRepository(rideApiInterface: RideApiInterface, ): RideRepositoryInterface {
        return RideRepository(rideApiInterface)
    }
}