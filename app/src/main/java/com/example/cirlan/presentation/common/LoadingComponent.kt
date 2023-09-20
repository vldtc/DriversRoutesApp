package com.example.cirlan.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cirlan.presentation.ui.theme.loading

@Composable
fun LoadingBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(loading)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary
        )
    }
}