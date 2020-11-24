package com.doctor.freenow.api

import com.doctor.freenow.model.Poi

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getVehicleList(
        p1Lat: String,
        p1Lon: String,
        p2Lat: String,
        p2Lon: String
    ): Poi = apiService.getVehicleList(p1Lat, p1Lon, p2Lat, p2Lon)
}