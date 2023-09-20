package com.example.cirlan.presentation.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.data.model.localdb.RoutesDBModel
import com.example.cirlan.data.repository.driversroutesapi.ApiRepo
import com.example.cirlan.data.repository.driversroutesdb.DBRepo
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped
import com.example.cirlan.domain.model.driversroutesmapper.RouteModelMapped
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: ApiRepo,
    private val dbRepo: DBRepo
) : ViewModel() {

    private val _drivers = MutableStateFlow<List<DriversDBModel>>(mutableListOf())
    val drivers: StateFlow<List<DriversDBModel>> = _drivers

    private val _routes = MutableStateFlow<List<RoutesDBModel>>(mutableListOf())
    val routes: StateFlow<List<RoutesDBModel>> = _routes

    private val _loadingState = MutableStateFlow(false)
    val loadingState : StateFlow<Boolean> = _loadingState

    init {
        viewModelScope.launch {
            storeNetworkDataToLocalDatabase()
        }
    }

    private suspend fun getDrivers(): List<DriverModelMapped?>? {
        return suspendCoroutine { continuation ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repo.getDrivers()
                    // Assuming response is a List<DriverModelMapped?>
                    withContext(Dispatchers.Main) {
                        continuation.resume(response)
                    }
                } catch (e: Exception) {
                    Log.d("ApiError!", e.toString())
                    withContext(Dispatchers.Main) {
                        continuation.resume(null) // Handle the error as needed
                    }
                }
            }
        }
    }

    private suspend fun getRoutes(): List<RouteModelMapped?>? {
        return suspendCoroutine { continuation ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repo.getRoutes()
                    withContext(Dispatchers.Main) {
                        continuation.resume(response)
                    }
                } catch (e: Exception) {
                    Log.d("ApiError!", e.toString())
                    withContext(Dispatchers.Main) {
                        continuation.resume(null)
                    }
                }
            }
        }
    }

    private suspend fun storeNetworkDataToLocalDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateLoadingState(true)

                val drivers = getDrivers()
                val routes = getRoutes()

                drivers?.forEach { driver ->
                    dbRepo.insertDriversData(
                        DriversDBModel(
                            id = driver?.id.toString(),
                            name = driver?.name
                        )
                    )
                }

                routes?.forEach { route ->
                    dbRepo.insertRoutesData(
                        RoutesDBModel(
                            id = route?.id ?: 0,
                            name = route?.name,
                            type = route?.type
                        )
                    )
                }

                updateLoadingState(false)
            }catch (e: Exception){
                Log.d("DBPopulationError!", e.toString())
                updateLoadingState(false)
            }
        }
    }

    fun getDriversFromDB(){
        viewModelScope.launch (Dispatchers.IO){
            try{
                val response = dbRepo.getAllDrivers()
                _drivers.value = response
            }catch (e: Exception){
                Log.d("DBFetchDriversError!", e.toString())
            }
        }
    }

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

    fun updateLoadingState(value: Boolean){
        _loadingState.value = value
    }

}