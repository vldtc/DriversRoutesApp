package com.example.cirlan.domain.usecase

import com.example.cirlan.data.model.localdb.RoutesDBModel

fun filterRoutesListByDriver(driverId: String, routes: List<RoutesDBModel>): List<RoutesDBModel> {

    val id = driverId.toIntOrNull() ?: 0
    val filteredList = mutableListOf<RoutesDBModel>()

    routes.forEach { route ->
        if(route.id == id) filteredList.add(route) }

    if(id % 2 == 0){
        val rTypeFilteredList = routes.filter { it.type == "R" }
        if(rTypeFilteredList.isNotEmpty()) filteredList.add(rTypeFilteredList[0])
    }

    if(id % 5 == 0){
        val cTypeFilteredList = routes.filter { it.type == "C" }
        if(cTypeFilteredList.isNotEmpty()) filteredList.add(cTypeFilteredList[1])
    }

    if(filteredList.isEmpty()){
        val iTypeFilteredList = routes.filter { it.type == "I" }
        if(iTypeFilteredList.isNotEmpty()) filteredList.add(iTypeFilteredList[iTypeFilteredList.size-1])
    }

    return filteredList
}