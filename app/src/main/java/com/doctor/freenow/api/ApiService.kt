package com.doctor.freenow.api

import com.doctor.freenow.model.Poi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("")
    suspend fun getVehicleList(@Query("p1Lat") p1Lat: String,
    @Query("p1Lon") p1Lon: String,
    @Query("p2Lat") p2Lat: String,
    @Query("p2Lon") p2Lon: String): List<Poi>
}