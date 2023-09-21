package com.example.cirlan.data.model.driversroutes


import com.google.gson.annotations.SerializedName

data class DriverModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = ""
)