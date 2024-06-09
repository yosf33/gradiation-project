package com.example.gradiationproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:ImageVector,
){

object Home:BottomBarScreen(
    route = "home",
    title = "",
    icon = Icons.Default.Home
)

object AddPost:BottomBarScreen(
    route = "addPost",
    title = "",
    icon = Icons.Default.Add
)

object Chat:BottomBarScreen(
    route = "chat",
    title = "",
    icon = Icons.Default.ChatBubble
)
object Profile:BottomBarScreen(
    route = "profile",
    title = "",
    icon = Icons.Default.Person
)
object AiModel:BottomBarScreen(
    route = "ai",
    title = "",
    icon = Icons.Default.Adb
)

}