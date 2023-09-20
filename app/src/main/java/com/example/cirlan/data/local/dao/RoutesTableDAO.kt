package com.example.cirlan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cirlan.data.model.localdb.RoutesDBModel

@Dao
interface RoutesTableDAO {

    @Query("SELECT * FROM routes_table")
    fun getAllRoutes(): List<RoutesDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoutesData(route: RoutesDBModel)
}
