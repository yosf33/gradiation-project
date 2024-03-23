package com.example.gradiationproject

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gradiationproject.data.LoginUIEvent
import com.example.gradiationproject.data.LoginUiState
import com.example.gradiationproject.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    var loginUiState = mutableStateOf(LoginUiState())
    var allValidationsPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {

                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )


            }

            is LoginUIEvent.PasswordChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {

                login()
            }

            else -> {}
        }
        validateDataWithRules()
    }

    private fun validateDataWithRules() {


        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )


        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )
        allValidationsPassed.value =
            emailResult.status && passwordResult.status

    }

    private fun login() {

        val email=loginUiState.value.email
        val password=loginUiState.value.password

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "inside_Login_success")
                Log.d(TAG, "isSuccessful= ${it.isSuccessful}")
            }.addOnFailureListener{
                Log.d(TAG, "inside_login_fail")
                Log.d(TAG, "Exception=${it.message}")
                Log.d(TAG, "Exception=${it.localizedMessage}")

            }

    }
}