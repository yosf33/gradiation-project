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
import androidx.navigation.compose.rememberNavController
import com.example.gradiationproject.R
import com.example.gradiationproject.components.ButtonComponent
import com.example.gradiationproject.components.DividerTextComponent
import com.example.gradiationproject.components.HeadingTextComponents
import com.example.gradiationproject.components.MyTextField
import com.example.gradiationproject.components.NormalTextComponents
import com.example.gradiationproject.components.PasswordTextField
import com.example.gradiationproject.components.UnderLinedTextComponents

@Composable
fun LoginScreen() {
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
                    .padding(start = 28.dp, end = 28.dp, top = 48.dp, bottom = 28.dp)
            ) {

                Column(Modifier.fillMaxSize()) {

                    NormalTextComponents(value = stringResource(id = R.string.login))
                    HeadingTextComponents(value = stringResource(id = R.string.welcome_back))
                    Spacer(Modifier.height(8.dp))
                    MyTextField(labelValue = stringResource(id = R.string.email),painterResource(id = R.drawable.email))
                    Spacer(Modifier.height(8.dp))
                    PasswordTextField(labelValue = stringResource(id = R.string.password),painterResource(id = R.drawable.password))
                    Spacer(Modifier.height(20.dp))
                    UnderLinedTextComponents(stringResource(id = R.string.forget_password))
                    Spacer(Modifier.height(40.dp))
                    ButtonComponent(stringResource(id = R.string.login))
                    Spacer(Modifier.height(20.dp))
                    DividerTextComponent()

                }
            }


        }

    }
}@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}