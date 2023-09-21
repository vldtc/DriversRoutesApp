package com.example.cirlan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cirlan.data.local.dao.DriversTableDAO
import com.example.cirlan.data.local.dao.RoutesTableDAO
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.data.model.localdb.RoutesDBModel

@Database(
    entities = [DriversDBModel::class, RoutesDBModel::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun driversTableDAO(): DriversTableDAO
    abstract fun routesTableDAO(): RoutesTableDAO
}