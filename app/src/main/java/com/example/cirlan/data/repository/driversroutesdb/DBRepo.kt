package com.example.cirlan.data.repository.driversroutesdb

import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.data.model.localdb.RoutesDBModel

interface DBRepo {
    fun getAllDrivers(): List<DriversDBModel>

    fun insertDriversData(driver: DriversDBModel)

    fun getAllRoutes(): List<RoutesDBModel>

    fun insertRoutesData(route: RoutesDBModel)

}