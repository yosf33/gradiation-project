package com.example.gradiationproject.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var registrationUiState = mutableStateOf(RegistrationUiState())

    private val TAG = LoginViewModel::class.simpleName

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.FirstNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is UIEvent.LastNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is UIEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )
                printState()
            }

            is UIEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
                printState()
            }

            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }

            else -> {}
        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp: ")
        printState()
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState: ")
        Log.d(TAG, registrationUiState.value.toString())
    }
}