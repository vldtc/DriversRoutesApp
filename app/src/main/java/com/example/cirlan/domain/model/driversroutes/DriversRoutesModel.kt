package com.example.cirlan.domain.model.driversroutes


import com.google.gson.annotations.SerializedName

data class DriversRoutesModel(
    @SerializedName("drivers")
    val drivers: List<DriverModel?>? = listOf(),
    @SerializedName("routes")
    val routes: List<RouteModel?>? = listOf()
)