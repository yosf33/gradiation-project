package com.example.gradiationproject.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.ActivityNavigator

sealed class Screen(val route: String){
    object Welcome:Screen(route = "welcome_screen")
//    object Home:Screen(route = "home_screen")

    //navigation edit #0
    object SignUpScreen:Screen(route = "signUp_screen")
    object TermsAndConditionsScreen:Screen(route = "terms_and_conditions_screen")
    object LoginScreen:Screen(route = "logIn_screen")
    object HomeScreen:Screen(route = "home_screen")




}




