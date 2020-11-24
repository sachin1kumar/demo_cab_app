package com.doctor.freenow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doctor.freenow.api.ApiHelper
import com.doctor.freenow.model.Poi
import com.doctor.freenow.utils.Resource
import com.doctor.freenow.utils.StringUtils
import kotlinx.coroutines.launch

class VehicleListViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val vehicles = MutableLiveData<Resource<Poi>>()

    fun fetchVehicles() {
        viewModelScope.launch {
            vehicles.postValue(Resource.loading(null))
            try {
                val usersFromApi = apiHelper.getVehicleList(StringUtils.p1Lat, StringUtils.p1Lon, StringUtils.p2Lat, StringUtils.p2Lon)
                vehicles.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                vehicles.postValue(Resource.error(StringUtils.ERROR_MSG, null))
            }
        }
    }

    fun getVehicles(): LiveData<Resource<Poi>> {
        return vehicles
    }

}