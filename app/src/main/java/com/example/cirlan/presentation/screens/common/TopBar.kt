package com.example.cirlan.presentation.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.cirlan.presentation.ui.theme.LineColour

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom(
    onBackButtonClick: () -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LineColour)
    ) {
        IconButton(onClick = { onBackButtonClick() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
        }
    }
}