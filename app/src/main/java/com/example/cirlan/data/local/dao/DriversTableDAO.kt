package com.example.cirlan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cirlan.data.model.localdb.DriversDBModel

@Dao
interface DriversTableDAO {

    @Query("SELECT * FROM drivers_table")
    fun getAllDrivers(): List<DriversDBModel>

    @Query("SELECT * FROM drivers_table WHERE id=:id")
    fun getDriverById(id: String): DriversDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDriversData(driver: DriversDBModel)
}
