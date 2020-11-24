package com.doctor.freenow.api

import com.doctor.freenow.model.Poi
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET(".")
    @JvmSuppressWildcards
    suspend fun getVehicleList(@QueryMap queryMap: Map<String, Any>): Poi
}