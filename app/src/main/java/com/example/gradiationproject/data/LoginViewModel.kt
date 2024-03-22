package com.example.gradiationproject.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gradiationproject.data.rules.Validator

class LoginViewModel : ViewModel() {
    var registrationUiState = mutableStateOf(RegistrationUiState())

    private val TAG = LoginViewModel::class.simpleName

    fun onEvent(event: UIEvent) {
        validateDataWithRules()
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
        validateDataWithRules()
    }

    private fun validateDataWithRules() {

        val fNameResult= Validator.validateFirstName(
            fName = registrationUiState.value.firstName
        )

        val lNameResult= Validator.validateLastName(
            lName = registrationUiState.value.lastName
        )

        val emailResult= Validator.validateEmail(
            email = registrationUiState.value.email
        )

        val passwordResult= Validator.validatePassword(
            password = registrationUiState.value.password
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fName=$fNameResult")
        Log.d(TAG, "fName=$lNameResult")
        Log.d(TAG, "fName=$emailResult")
        Log.d(TAG, "fName=$passwordResult")

        registrationUiState.value=registrationUiState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

    }

    private fun printState() {
        Log.d(TAG, "Inside_printState: ")
        Log.d(TAG, registrationUiState.value.toString())
    }
}