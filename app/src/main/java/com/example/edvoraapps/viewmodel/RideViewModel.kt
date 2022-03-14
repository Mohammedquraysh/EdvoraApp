package com.example.edvoraapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edvoraapps.data.RidesData
import com.example.edvoraapps.repository.RideRepositoryInterface
import com.example.edvoraapps.util.ApiCallNetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class RideViewModel @Inject constructor(private val rideRepository: RideRepositoryInterface): ViewModel() {

    //ride LiveData
    private val _rideResponseLivedata: MutableLiveData<ApiCallNetworkResource<RidesData>> = MutableLiveData()
    val rideResponseLiveData: LiveData<ApiCallNetworkResource<RidesData>> = _rideResponseLivedata



    //ride error handling
    fun getAllRide(){

        viewModelScope.launch {
            _rideResponseLivedata.postValue(ApiCallNetworkResource.Loading())
            try {
                delay(1)
                val response = rideRepository.getRide()
                if (response.isSuccessful) {
                    _rideResponseLivedata.postValue(ApiCallNetworkResource.Success(response.message(),response.body()))
                }
                else  {
                    _rideResponseLivedata.postValue(ApiCallNetworkResource.Error(response.message()))
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                when(e){
                    is IOException ->{
                        _rideResponseLivedata.postValue(ApiCallNetworkResource.Error( message =
                        "Network Failure, please check your internet connection"
                        ))
                    }
                    is NullPointerException -> {
                        _rideResponseLivedata.postValue(ApiCallNetworkResource.Error(
                            "Invalid data"
                        ))
                    }
                    else->{
                        _rideResponseLivedata.postValue(ApiCallNetworkResource.Error(message =
                        "an error occur please try again later"
                        ))
                    }
                }
            }
        }
    }
}