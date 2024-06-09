package com.example.gradiationproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gradiationproject.BottomBarScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.AddPostScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.AiModelScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.ChatListScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.HomeScreen
import com.example.gradiationproject.screen.bottomNavigationScreens.ProfileScreen
import com.example.gradiationproject.viewmodel.AddPostViewModel
import com.example.gradiationproject.viewmodel.HomeScreenViewModel

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {

        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(viewModel =  HomeScreenViewModel())
        }

        composable(route = BottomBarScreen.AddPost.route) {
            AddPostScreen(viewModel = AddPostViewModel())
        }

        composable(route = BottomBarScreen.Chat.route) {
            ChatListScreen()
        }

        composable(route = BottomBarScreen.AiModel.route) {
            AiModelScreen()
        }

        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}