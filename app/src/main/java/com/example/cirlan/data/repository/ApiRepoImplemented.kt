package com.example.cirlan.data.repository

import com.example.cirlan.data.remote.ApiRequest
import com.example.cirlan.data.model.driversroutes.DriverModel
import com.example.cirlan.data.model.driversroutes.RouteModel
import javax.inject.Inject

class ApiRepoImplemented @Inject constructor(
    private val apiRequest: ApiRequest
)  : ApiRepo {

    override suspend fun getDrivers(): List<DriverModel?>? = apiRequest.getDrivers()

    override suspend fun getRoutes(): List<RouteModel?>? = apiRequest.getRoutes()

}