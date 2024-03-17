package com.example.gradiationproject.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gradiationproject.R
import com.example.gradiationproject.components.HeadingTextComponents

@Composable
fun TermsAndConditionsScreen() {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp)) {

        HeadingTextComponents(value = stringResource(id= R.string.terms_and_conditions_header))
    }
}

@Preview
@Composable
fun TermsPreview() {
    TermsAndConditionsScreen()
}