package com.example.gradiationproject.screen.bottomNavigationScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradiationproject.viewmodel.AddPostViewModel

@Composable
fun AddPostScreen(viewModel: AddPostViewModel) {
//    Box(modifier= Modifier
//        .fillMaxSize()
//        .background(Color.Green), contentAlignment = Alignment.Center) {
//        Text(text = "AddPostScreen", color = Color.White, fontSize = 24.sp)
//
//    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { viewModel.addPostToFirestore() }) {
                Text("Add Post to Firestore")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AddPostScreenPreview() {
    AddPostScreen(viewModel = AddPostViewModel())
}