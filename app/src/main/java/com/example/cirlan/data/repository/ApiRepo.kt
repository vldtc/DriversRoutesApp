package com.example.cirlan.data.repository

import com.example.cirlan.data.model.driversroutes.DriverModel
import com.example.cirlan.data.model.driversroutes.RouteModel

interface ApiRepo {
    suspend fun getDrivers(): List<DriverModel?>?

    suspend fun getRoutes(): List<RouteModel?>?

}