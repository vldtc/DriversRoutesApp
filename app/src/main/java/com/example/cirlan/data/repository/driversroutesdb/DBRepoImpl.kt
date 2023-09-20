package com.example.cirlan.data.repository.driversroutesdb

import com.example.cirlan.data.local.dao.DriversTableDAO
import com.example.cirlan.data.local.dao.RoutesTableDAO
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.data.model.localdb.RoutesDBModel

class DBRepoImpl(
    private val driversDAO: DriversTableDAO,
    private val routesDAO: RoutesTableDAO
    ): DBRepo {

    override fun getAllDrivers(): List<DriversDBModel> = driversDAO.getAllDrivers()

    override fun insertDriversData(driver: DriversDBModel) = driversDAO.insertDriversData(driver)

    override fun getAllRoutes(): List<RoutesDBModel> = routesDAO.getAllRoutes()

    override fun insertRoutesData(route: RoutesDBModel) = routesDAO.insertRoutesData(route)


}