package com.doctor.freenow.api

import com.doctor.freenow.model.Poi

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getVehicleList(queryMap: Map<String, Any>): Poi = apiService.getVehicleList(queryMap)
}