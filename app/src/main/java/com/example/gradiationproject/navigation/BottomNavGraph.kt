package com.example.gradiationproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gradiationproject.BottomBarScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.AddPostScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.ChatListScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.HomeScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){

    composable(route = BottomBarScreen.Home.route) {
        HomeScreen()
    }

    composable(route = BottomBarScreen.AddPost.route) {
        AddPostScreen()
    }

    composable(route = BottomBarScreen.Chat.route) {
        ChatListScreen()
    }
}
}