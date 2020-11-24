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
                val usersFromApi = apiHelper.getVehicleList(getQueryMap())
                vehicles.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                vehicles.postValue(Resource.error(StringUtils.ERROR_MSG, null))
            }
        }
    }

    private fun getQueryMap(): Map<String, Any> {
        val queryMap = HashMap<String, Any>()
        queryMap[StringUtils.FIRST_LATITUDE] = StringUtils.FIRST_LATITUDE_VAL
        queryMap[StringUtils.FIRST_LONGITUDE] = StringUtils.FIRST_LONGITUDE_VAL
        queryMap[StringUtils.SECOND_LATITUDE] = StringUtils.SECOND_LATITUDE_VAL
        queryMap[StringUtils.SECOND_LONGITUDE] = StringUtils.SECOND_LONGITUDE_VAL
        return queryMap
    }

    fun getVehicles(): LiveData<Resource<Poi>> {
        return vehicles
    }

}