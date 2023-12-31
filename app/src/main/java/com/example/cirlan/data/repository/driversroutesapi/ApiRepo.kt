package com.example.cirlan.data.repository.driversroutesapi

import com.example.cirlan.data.model.driversroutes.DriverModel
import com.example.cirlan.data.model.driversroutes.DriversRoutesModel
import com.example.cirlan.data.model.driversroutes.RouteModel
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped
import com.example.cirlan.domain.model.driversroutesmapper.RouteModelMapped

interface ApiRepo {
    suspend fun getList(): DriversRoutesModel

    suspend fun getDrivers(): List<DriverModelMapped?>?

    suspend fun getRoutes(): List<RouteModelMapped?>?

}