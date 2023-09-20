package com.example.cirlan.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.domain.model.driversroutesmapper.DriverModelMapped
import com.example.cirlan.presentation.common.LoadingBar
import com.example.cirlan.presentation.ui.theme.LineColour

@Composable
fun MainScreen() {

    val viewModel = hiltViewModel<MainViewModel>()
    val drivers by viewModel.drivers.collectAsState()
    val routes by viewModel.routes.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()

    viewModel.getDriversFromDB()
    viewModel.getRoutesFromDB()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            DriversLegend()
        }
        items(drivers.size ?: 0) {
            DriversItem(drivers[it])
        }
    }

    if (loadingState) LoadingBar()

}

@Composable
fun DriversItem(
    driver: DriversDBModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .background(LineColour)
                    .zIndex(1f)// Set the background color
            ) {
                Text(text = "")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = driver.id,
                    color = LineColour,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = driver.name.toString(),
                    color = LineColour,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun DriversLegend() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "ID",
            color = LineColour,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
        Text(
            text = "Name",
            color = LineColour,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
    Divider(thickness = 1.dp, color = LineColour)
    Spacer(modifier = Modifier.height(16.dp))
}