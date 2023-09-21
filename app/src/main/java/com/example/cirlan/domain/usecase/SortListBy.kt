package com.example.cirlan.domain.usecase

import com.example.cirlan.data.model.localdb.DriversDBModel

fun sortListBy(drivers: List<DriversDBModel>, sortBy: String): List<DriversDBModel>{

    return when(sortBy){
        "First\nname" -> drivers.sortedBy { it.name }
        "Last\nname" -> drivers.sortedBy { it.name?.substringAfter(" ") }
        "ID\nnumber" -> drivers.sortedWith(compareBy { it.id.toIntOrNull() ?: Int.MAX_VALUE })
        else -> drivers
    }
}