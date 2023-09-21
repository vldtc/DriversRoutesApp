package com.example.cirlan.data.model.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers_table")
data class DriversDBModel(
    @PrimaryKey val id: String = "",
    val name: String? = ""
)
