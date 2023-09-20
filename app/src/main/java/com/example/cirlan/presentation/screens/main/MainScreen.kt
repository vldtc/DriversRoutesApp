package com.example.cirlan.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped

@Composable
fun MainScreen() {

    val viewModel = hiltViewModel<MainViewModel>()
    val drivers by viewModel.drivers.collectAsState()
    val routes by viewModel.routes.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(drivers?.size ?: 0) {
            DriversItem(drivers?.get(it))
        }
    }

}

@Composable
fun DriversItem(
    driver: DriverModelMapped?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = driver?.id.toString())
        Text(text = driver?.name.toString())
    }
}