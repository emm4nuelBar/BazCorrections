package com.example.baz_android_capstone.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun Principal(
    navController: NavController
) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "Aquí va el menú principal",
            style = MaterialTheme.typography.h4
        )
    }
}