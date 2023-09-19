package com.example.cirlan.data.repository

import com.example.cirlan.data.model.driversroutes.DriverModel
import com.example.cirlan.data.model.driversroutes.RouteModel
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped
import com.example.cirlan.domain.model.driversroutesmapper.RouteModelMapped

interface ApiRepo {
    suspend fun getDrivers(): List<DriverModelMapped?>?

    suspend fun getRoutes(): List<RouteModelMapped?>?

}