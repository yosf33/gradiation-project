@file:Suppress("DEPRECATION")

package com.example.gradiationproject.screen.bottomNavigationScreens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gradiationproject.ui.theme.mediuem
import com.example.gradiationproject.ui.theme.secondary
import com.example.gradiationproject.viewmodel.AddPostViewModel
import com.example.gradiationproject.viewmodel.PdfViewModel
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun AddPostScreen(viewModel: AddPostViewModel,pdfViewModel: PdfViewModel = viewModel()) {
        BottomSheetDemo(viewModel)
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDemo(viewModel: AddPostViewModel, pdfViewModel: PdfViewModel = viewModel()) {
    val pdfFiles: List<File> by pdfViewModel.pdfFiles.collectAsState(emptyList())

    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var numberOfParts by remember { mutableStateOf(5) }

    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let {
            Log.d("PdfScreen", "PDF selected: $uri")
            pdfViewModel.splitPdf(context, it, numberOfParts.toString())
        }
    }



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



                    //region bottomSheetTitle
                    Text(
                        text = "Add Post",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    //endregion

                    Column {
                        Spacer(modifier = Modifier.height(16.dp))

                        //region title TextField
                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ), label = { Text(text = "Title") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            readOnly = false // Make it editable
                        )
                        //endregion
                        Spacer(modifier = Modifier.height(8.dp))
                        //region Price TextField
                        TextField(
                            value = price,
                            onValueChange = { price = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                            label = { Text(text = "Price") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            readOnly = false // Make it editable
                        )
                        //endregion
                        Spacer(modifier = Modifier.height(8.dp))
                        //region description TextField
                        TextField(
                            value = description,
                            onValueChange = { description = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
                            minLines = 5,
                            label = { Text(text = "Description") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            readOnly = false // Make it editable
                        )
                        //endregion
                        Spacer(modifier = Modifier.height(8.dp))
                        //region number of parts TextField
                        NumberTextField(
                            initialValue = numberOfParts,
                            onValueChange = { numberOfParts = it }
                        )
                        //endregion
                        Spacer(modifier = Modifier .fillMaxHeight(0.1f))

                        Button(onClick = { launcher.launch(arrayOf("application/pdf")) }) {
                            Text(text = "Choose PDF")
                        }
                        Spacer(modifier = Modifier .fillMaxHeight(0.3f))
                        //region Add post Button
                        Button(
                            onClick = {

                                viewModel.addPostToFirestore(
                                    title,
                                    price,
                                    description,
                                    numberOfParts
                                )
                                coroutineScope.launch {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                    snackbarHostState.showSnackbar("Post Saved")
                                }

                                title = ""
                                price = ""
                                description = ""
                                numberOfParts = 0
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            Text("Publish")
                        }
                        //endregion


                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)

                    .background(
                        color = mediuem
                    )
                    .padding(32.dp),

                )
        },
        sheetPeekHeight = 0.dp,

        ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Log.i("testo", "number of parts is: "+numberOfParts.toString())
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Log.d("PdfViewModel", "pdfFiles: "+pdfFiles)
                    items(pdfFiles.sortedBy { it.name }) { file ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { pdfViewModel.openPdf(context, file) }
                                .padding(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                text = file.name,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }

        //Add button to open bottom sheet
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 48.dp, end = 16.dp), contentAlignment = Alignment.BottomEnd
        ) {

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
    AddPostScreen(viewModel = AddPostViewModel(), pdfViewModel = PdfViewModel())
}

@Composable
fun NumberTextField(
    initialValue: Int,
    onValueChange: (Int) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue.toString())) }

    // Limiting the input to numbers only
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textFieldValue,label = { Text(text = "Number of parts") },
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

