package com.example.cirlan.presentation.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cirlan.data.repository.driversroutesapi.ApiRepo
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped
import com.example.cirlan.domain.model.driversroutesmapper.RouteModelMapped
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val repo: ApiRepo
) : ViewModel() {

    private val _drivers = MutableStateFlow<List<DriverModelMapped?>?>(mutableListOf())
    val drivers: StateFlow<List<DriverModelMapped?>?> = _drivers

    private val _routes = MutableStateFlow<List<RouteModelMapped?>?>(mutableListOf())
    val routes: StateFlow<List<RouteModelMapped?>?> = _routes

    init {
        getDrivers()
        getRoutes()
    }

    fun getDrivers(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repo.getDrivers()
                _drivers.value = response
            }catch (e: Exception){
                Log.d("ApiError!", e.toString())
            }
        }
    }

    fun getRoutes(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repo.getRoutes()
                _routes.value = response
            }catch (e: Exception){
                Log.d("ApiError!", e.toString())
            }
        }
    }
}