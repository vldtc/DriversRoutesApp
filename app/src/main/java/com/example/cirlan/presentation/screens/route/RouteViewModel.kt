package com.example.cirlan.presentation.screens.route

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.data.model.localdb.RoutesDBModel
import com.example.cirlan.data.repository.driversroutesdb.DBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val dbRepo: DBRepo
): ViewModel() {

    init{
        getRoutesFromDB()
    }

    private val _driver = MutableStateFlow(DriversDBModel())
    val driver: StateFlow<DriversDBModel> = _driver

    private val _routes = MutableStateFlow<List<RoutesDBModel>>(mutableListOf())
    val routes: StateFlow<List<RoutesDBModel>> = _routes

    fun getRoutesFromDB(){
        viewModelScope.launch (Dispatchers.IO){
            try{
                val response = dbRepo.getAllRoutes()
                _routes.value = response
            }catch (e: Exception){
                Log.d("DBFetchRoutesError!", e.toString())
            }
        }
    }

    fun getDriverFromDB(id:String){
        viewModelScope.launch (Dispatchers.IO){
            try{
                val response = dbRepo.getDriverById(id)
                _driver.value = response
            }catch (e: Exception){
                Log.d("DBFetchDriversError!", e.toString())
            }
        }
    }
}