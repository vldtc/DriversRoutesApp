package com.example.cirlan.data.remote

import com.example.cirlan.data.model.driversroutes.DriverModel
import com.example.cirlan.data.model.driversroutes.RouteModel
import retrofit2.http.GET

interface ApiRequest {

    @GET(ApiDetails.END_POINT)
    suspend fun getDrivers(): List<DriverModel?>?

    @GET(ApiDetails.END_POINT)
    suspend fun getRoutes(): List<RouteModel?>?

}