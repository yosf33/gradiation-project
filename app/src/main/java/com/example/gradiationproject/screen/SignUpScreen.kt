package com.example.gradiationproject.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gradiationproject.R
import com.example.gradiationproject.components.ButtonComponent
import com.example.gradiationproject.components.CheckBoxComponent
import com.example.gradiationproject.components.ClickableLoginTextComponent
import com.example.gradiationproject.components.DividerTextComponent
import com.example.gradiationproject.components.HeadingTextComponents
import com.example.gradiationproject.components.MyTextField
import com.example.gradiationproject.components.NormalTextComponents
import com.example.gradiationproject.components.PasswordTextField
import com.example.gradiationproject.data.SignUpViewModel
import com.example.gradiationproject.data.SignUpUIEvent

@Composable
fun SignUpScreen(navController: NavController, signUpViewModel: SignUpViewModel = viewModel()) {
    val secondaryColor = colorResource(id = R.color.medium_purple)

    Surface(
        color = secondaryColor,
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.effect_ic),
                contentDescription = "sign up"
            )

            Surface(
                color = secondaryColor,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)
            ) {

                Column(Modifier.fillMaxSize()) {

                    NormalTextComponents(value = stringResource(id = R.string.hello))
                    HeadingTextComponents(value = stringResource(id = R.string.create_account))
                    Spacer(Modifier.height(20.dp))
                    MyTextField(
                        labelValue = stringResource(id = R.string.first_name),
                        painterResource(id = R.drawable.person),
                        onTextSelected = {

                            signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                        }, errorStatus = signUpViewModel.registrationUiState.value.firstNameError
                    )
                    Spacer(Modifier.height(8.dp))
                    MyTextField(
                        labelValue = stringResource(id = R.string.last_name),
                        painterResource(id = R.drawable.person),
                        onTextSelected = {

                            signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                        }, errorStatus = signUpViewModel.registrationUiState.value.lastNameError
                    )
                    Spacer(Modifier.height(8.dp))
                    MyTextField(
                        labelValue = stringResource(id = R.string.email),
                        painterResource(id = R.drawable.email),
                        onTextSelected = {

                            signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                        }, errorStatus = signUpViewModel.registrationUiState.value.emailError
                    )
                    Spacer(Modifier.height(8.dp))
                    PasswordTextField(
                        labelValue = stringResource(id = R.string.password),
                        painterResource(id = R.drawable.password),
                        onTextSelected = {

                            signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                        }, errorStatus = signUpViewModel.registrationUiState.value.passwordError
                    )


                    Spacer(Modifier.height(8.dp))
                    CheckBoxComponent(
                        stringResource(id = R.string.terms_and_conditions),
                        navController
                    )
                    Spacer(Modifier.height(40.dp))
                    ButtonComponent(stringResource(id = R.string.register), {
                        signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                    },isEnabled = signUpViewModel.allValidationsPassed.value)
                    DividerTextComponent()
                    Spacer(Modifier.height(8.dp))
                    ClickableLoginTextComponent(navController)
                }
            }
        }
    }

}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}

