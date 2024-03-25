package com.example.gradiationproject.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gradiationproject.screen.bottomNavigationScreens.HomeScreen
import com.example.gradiationproject.screen.LoginScreen
import com.example.gradiationproject.screen.SignUpScreen
import com.example.gradiationproject.screen.TermsAndConditionsScreen
import com.example.gradiationproject.screen.WelcomeScreen
import com.example.gradiationproject.viewmodel.HomeScreenViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination:String
) {
   NavHost(
       navController = navController,
       startDestination = startDestination,
       builder = {
       composable(route=Screen.Welcome.route){
           WelcomeScreen(navController=navController)
       }

           composable(route = Screen.SignUpScreen.route) {
               SignUpScreen(navController = navController)
           }
           composable(route =Screen.TermsAndConditionsScreen.route) {
               TermsAndConditionsScreen()
           }
           composable(route =Screen.LoginScreen.route) {
               LoginScreen()
           }

           composable(route =Screen.HomeScreen.route) {
               HomeScreen(viewModel = HomeScreenViewModel())
           }


       })

}