package com.example.gradiationproject.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gradiationproject.screen.HomeScreen
import com.example.gradiationproject.screen.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
   NavHost(
       navController = navController,
       startDestination = Screen.Welcome.route,
       builder = {
       composable(route=Screen.Welcome.route){
           WelcomeScreen(navController=navController)
       }
       composable(route=Screen.Home.route){
               HomeScreen()
           }
   })

}