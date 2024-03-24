package com.example.gradiationproject.screen.bottomNavigationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {
    Box(modifier= Modifier
        .fillMaxSize()
        .background(Color.Green), contentAlignment = Alignment.Center) {
        Text(text = "SettingsScreen", color = Color.White, fontSize = 24.sp)

    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}