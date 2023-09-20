package com.example.cirlan.di

import android.content.Context
import androidx.room.Room
import com.example.cirlan.data.local.LocalDatabase
import com.example.cirlan.data.local.dao.DriversTableDAO
import com.example.cirlan.data.local.dao.RoutesTableDAO
import com.example.cirlan.data.repository.driversroutesdb.DBRepo
import com.example.cirlan.data.repository.driversroutesdb.DBRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, LocalDatabase::class.java, "Local Database").build()

    @Provides
    fun provideDriversDAO(database: LocalDatabase) = database.driversTableDAO()

    @Provides
    fun provideRoutesDAO(database: LocalDatabase) = database.routesTableDAO()

    @Provides
    fun provideDBRepo(
        driversTableDAO: DriversTableDAO,
        routesTableDAO: RoutesTableDAO
    ): DBRepo = DBRepoImpl(driversTableDAO, routesTableDAO)
}