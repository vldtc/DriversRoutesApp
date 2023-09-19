package com.example.cirlan.domain.mapper

import com.example.cirlan.common.Mapper
import com.example.cirlan.data.model.driversroutes.RouteModel
import com.example.cirlan.domain.model.driversroutesmapper.RouteModelMapped

class RouteModelMapper: Mapper<List<RouteModel?>, List<RouteModelMapped>> {

    override fun mapFrom(from: List<RouteModel?>): List<RouteModelMapped> {
        val routesList = mutableListOf<RouteModelMapped>()
        for(route in from){
            val routeItem = RouteModelMapped(
                id = route?.id,
                name = route?.name,
                type = route?.type
            )
            routesList.add(routeItem)
        }
        return routesList
    }

}