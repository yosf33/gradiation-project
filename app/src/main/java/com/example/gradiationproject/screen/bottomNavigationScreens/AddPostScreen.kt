@file:Suppress("DEPRECATION")

package com.example.gradiationproject.screen.bottomNavigationScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradiationproject.R
import com.example.gradiationproject.ui.theme.mediuem
import com.example.gradiationproject.ui.theme.primary
import com.example.gradiationproject.ui.theme.secondary
import com.example.gradiationproject.viewmodel.AddPostViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.TextField as TextField1

@Composable
fun AddPostScreen(viewModel: AddPostViewModel) {
    BottomSheetDemo(viewModel)

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDemo(viewModel: AddPostViewModel) {


//Lets define bottomSheetScaffoldState which will hold the state of Scaffold
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            //Ui for bottom sheet
            Column(
                content = {

                    Spacer(modifier = Modifier.padding(32.dp))
                    Text(
                        text = "Add Post",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                  Column {
                      Spacer(modifier = Modifier.height(16.dp))
                      TextField1(
                          value = "Title", // Initial value
                          onValueChange = { /* Handle value change */ },
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(bottom = 8.dp),
                          textStyle = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold),
                          keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                          readOnly = false // Make it editable
                      )

                      TextField1(
                          value = "price", // Initial value
                          onValueChange = { /* Handle value change */ },
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(bottom = 8.dp),
                          textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                          keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                          readOnly = false // Make it editable
                      )

                      TextField1(
                          value = "Description", // Initial value
                          onValueChange = { /* Handle value change */ },
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(bottom = 8.dp),
                          textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                          keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                          readOnly = false // Make it editable
                      )

                      NumberTextField(
                          initialValue = 5, // Initial value
                          onValueChange = { newValue ->
                          }
                      )
                      Spacer(modifier = Modifier.padding(16.dp))

                      Button(onClick = { viewModel.addPostToFirestore() },modifier=Modifier.fillMaxWidth().height(40.dp)) {
                          Text("Add Post to Firestore")
                      }




                  }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)

                    .background(
                        color =mediuem
                    )
                    .padding(32.dp),

                )
        },
        sheetPeekHeight = 0.dp,

        ) {

        //Add button to open bottom sheet
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 48.dp,end=16.dp),contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(32.dp),
                onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }

                }, backgroundColor = secondary

            ) {
                Icon(Icons.Filled.Add, contentDescription = "Expand/Collapse Bottom Sheet")
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun AddPostScreenPreview() {
    AddPostScreen(viewModel = AddPostViewModel())
}

@Composable
fun NumberTextField(
    initialValue: Int,
    onValueChange: (Int) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue.toString())) }

    // Limiting the input to numbers only
    TextField1(modifier = Modifier
        .fillMaxWidth(),
        value = textFieldValue,
        onValueChange = { value ->
            if (value.text.matches(Regex("[0-9]*"))) {
                textFieldValue = value
                val intValue = value.text.toIntOrNull() ?: 0
                if (intValue in 1..200) {
                    onValueChange(intValue)
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
}