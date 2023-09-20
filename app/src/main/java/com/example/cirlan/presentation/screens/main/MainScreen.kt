package com.example.cirlan.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cirlan.data.model.localdb.DriversDBModel
import com.example.cirlan.domain.usecase.sortListBy
import com.example.cirlan.presentation.common.LoadingBar
import com.example.cirlan.presentation.ui.theme.LineColour

@Composable
fun MainScreen(
    onDriverClick: (String) -> Unit
) {

    val viewModel = hiltViewModel<MainViewModel>()
    val drivers by viewModel.drivers.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()

    viewModel.getDriversFromDB()
    viewModel.getRoutesFromDB()

    MainScreenContent(
        drivers = drivers,
        loadingState = loadingState,
        onDriverClick = { driverId ->
            onDriverClick(driverId)
        })

}

@SuppressLint("RememberReturnType")
@Composable
fun MainScreenContent(
    drivers: List<DriversDBModel>,
    loadingState: Boolean,
    onDriverClick: (String) -> Unit
) {

    val (sortBy, setSortBy) = remember { mutableStateOf("First\nname") }

    val sortedList = remember(drivers, sortBy) {
        sortListBy(drivers, sortBy)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            DriversFilterSection(onSortByChanged = setSortBy)
        }
        item {
            DriversLegend()
        }
        items(sortedList.size ?: 0) {
            DriversItem(sortedList[it], onDriverClick = { driverId ->
                onDriverClick(driverId)
            })
        }
    }

    if (loadingState) LoadingBar()
}

@Composable
fun DriversItem(
    driver: DriversDBModel,
    onDriverClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                onDriverClick(driver.id)
            },
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

@Composable
fun DriversLegend() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DriversFilterSection(
    onSortByChanged: (String) -> Unit
) {

    val radioOptions = listOf("First\nname", "Last\nname", "ID\nnumber")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Row(
        Modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Filter by",
            color = LineColour,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
        )
        radioOptions.forEach { text ->
            AnimatedContent(targetState = selectedOption) { selectedOption ->
                Row(
                    Modifier
                        .height(40.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                onSortByChanged(text)
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (text == selectedOption) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.primaryContainer
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 20.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Divider(thickness = 1.dp, color = LineColour)
    Spacer(modifier = Modifier.height(4.dp))
}