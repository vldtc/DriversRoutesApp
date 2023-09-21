package com.example.cirlan.presentation.screens.route

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.data.model.localdb.RoutesDBModel
import com.example.cirlan.domain.usecase.filterRoutesListByDriver
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

@SuppressLint("RememberReturnType")
@Composable
fun RouteScreenContent(
    modifier: Modifier,
    driver: DriversDBModel,
    routes: List<RoutesDBModel>
) {

    val filteredList = filterRoutesListByDriver(driver.id, routes)

    LazyColumn(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            DriversLegend(driver = driver)
        }
       items(filteredList.size){
            RouteItem(route = filteredList[it])
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

@Composable
fun RouteItem(
    route: RoutesDBModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = route.type.toString(),
                    color = LineColour,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = route.name.toString(),
                    color = LineColour,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            }
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .background(LineColour)
                    .zIndex(1f)// Set the background color
            ) {
                Text(text = "")
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}