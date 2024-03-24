package com.example.gradiationproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:ImageVector,
){

object Home:BottomBarScreen(
    route = "home",
    title = "Home",
    icon = Icons.Default.Home
)

object AddPost:BottomBarScreen(
    route = "addPost",
    title = "AddPost",
    icon = Icons.Default.Add
)

object Chat:BottomBarScreen(
    route = "chat",
    title = "Chat",
    icon = Icons.Default.ChatBubble
)
}