package com.doctor.freenow.api

import com.doctor.freenow.model.Poi

interface ApiHelper {

    suspend fun getVehicleList(p1Lat: String, p1Lon: String, p2Lat: String, p2Lon: String): List<Poi>
}