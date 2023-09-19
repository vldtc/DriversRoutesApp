package com.example.cirlan.domain.model.driversroutes


import com.google.gson.annotations.SerializedName

data class RouteModel(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("type")
    val type: String? = ""
)