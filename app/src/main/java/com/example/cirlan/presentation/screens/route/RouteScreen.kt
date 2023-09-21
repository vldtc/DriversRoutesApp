package com.example.cirlan.presentation.screens.route

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.data.model.localdb.RoutesDBModel
import com.example.cirlan.presentation.screens.common.TopBarCustom
import com.example.cirlan.presentation.ui.theme.LineColour

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteScreen(
    id: String,
    onBackButtonClicked: () -> Unit
) {

    val viewModel = hiltViewModel<RouteViewModel>()
    val driver by viewModel.driver.collectAsState()
    val routes by viewModel.routes.collectAsState()

    viewModel.getDriverFromDB(id)

    Scaffold(
        topBar = {
            TopBarCustom(
                onBackButtonClick = {
                    onBackButtonClicked()
                }
            )
        }
    ) { contentPadding ->
        RouteScreenContent(Modifier.padding(contentPadding), driver, routes)
    }

}

@Composable
fun RouteScreenContent(
    modifier: Modifier,
    driver: DriversDBModel,
    routes: List<RoutesDBModel>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            DriversLegend(driver = driver)
        }
    }
}

@Composable
fun DriversLegend(
    driver: DriversDBModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
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
    Spacer(modifier = Modifier.height(4.dp))
    Divider(thickness = 1.dp, color = LineColour)
    Spacer(modifier = Modifier.height(16.dp))
}