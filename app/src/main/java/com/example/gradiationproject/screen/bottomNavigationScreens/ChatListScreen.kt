package com.example.gradiationproject.screen.bottomNavigationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ChatListScreen() {
    Box(modifier= Modifier
        .fillMaxSize()
        .background(Color.Blue), contentAlignment = Alignment.Center) {
        Text(text = "ChatListScreen", color = Color.White, fontSize = 24.sp)

    }
}

@Preview(showBackground = true)
@Composable
fun ChatListScreenPreview() {
    ChatListScreen()
}