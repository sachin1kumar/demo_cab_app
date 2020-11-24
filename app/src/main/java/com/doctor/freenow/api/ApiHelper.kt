package com.doctor.freenow.api

import com.doctor.freenow.model.Poi

interface ApiHelper {

    suspend fun getVehicleList(queryMap: Map<String, Any>): Poi
}