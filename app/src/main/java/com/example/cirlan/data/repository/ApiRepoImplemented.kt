package com.example.cirlan.data.repository

import com.example.cirlan.data.remote.ApiRequest
import com.example.cirlan.data.model.driversroutes.DriverModel
import com.example.cirlan.data.model.driversroutes.DriversRoutesModel
import com.example.cirlan.data.model.driversroutes.RouteModel
import com.example.cirlan.domain.mapper.DriverModelMapper
import com.example.cirlan.domain.mapper.RouteModelMapper
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped
import com.example.cirlan.domain.model.driversroutesmapper.RouteModelMapped
import javax.inject.Inject

class ApiRepoImplemented @Inject constructor(
    private val apiRequest: ApiRequest,
    private val driverModelMapper: DriverModelMapper,
    private val routeModelMapper: RouteModelMapper
)  : ApiRepo {

    override suspend fun getList(): DriversRoutesModel = apiRequest.getList()

    override suspend fun getDrivers(): List<DriverModelMapped>? {
        val response = getList().drivers
        return response?.let { driverModelMapper.mapFrom(it) }
    }

    override suspend fun getRoutes(): List<RouteModelMapped?>? {
        val response = getList().routes
        return response?.let { routeModelMapper.mapFrom(it) }
    }

}