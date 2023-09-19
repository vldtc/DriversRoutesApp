package com.example.cirlan.domain.mapper

import com.example.cirlan.common.Mapper
import com.example.cirlan.data.model.driversroutes.DriverModel
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped

class DriverModelMapper : Mapper<List<DriverModel?>, List<DriverModelMapped>> {

    override fun mapFrom(from: List<DriverModel?>): List<DriverModelMapped> {
        val driversList = mutableListOf<DriverModelMapped>()
        for(driver in from){
            val driverItem = DriverModelMapped(
                id = driver?.id,
                name = driver?.name
            )
            driversList.add(driverItem)
        }
        return driversList
    }
}