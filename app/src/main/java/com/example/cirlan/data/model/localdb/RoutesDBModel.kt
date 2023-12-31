package com.example.cirlan.data.model.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes_table")
data class RoutesDBModel(
    @PrimaryKey val id: Int = 0,
    val name: String? = "",
    val type: String? = ""
)
