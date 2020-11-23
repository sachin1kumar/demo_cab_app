package com.doctor.freenow.api

import com.doctor.freenow.model.Poi

class ApiHelperImpl(
    private val apiService: ApiService,
    private val p1Lat: String,
    private val p1Lon: String,
    private val p2Lat: String,
    private val p2Lon: String
) : ApiHelper {

    override suspend fun getVehicleList(
        p1Lat: String,
        p1Lon: String,
        p2Lat: String,
        p2Lon: String
    ): List<Poi> = apiService.getVehicleList(p1Lat, p1Lon, p2Lat, p2Lon)
}