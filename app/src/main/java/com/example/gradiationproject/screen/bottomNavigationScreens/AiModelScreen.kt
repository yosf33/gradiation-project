package com.example.gradiationproject.screen.bottomNavigationScreens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gradiationproject.R
import com.example.gradiationproject.UploadButton
import com.example.gradiationproject.viewmodel.AiViewModel

@Composable
fun AiModelScreen(aiViewModel: AiViewModel = viewModel()) {

    val context = LocalContext.current
    var pdfUri by remember { mutableStateOf<Uri?>(null) }
    var audioUri by remember { mutableStateOf<Uri?>(null) }
    val pdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            pdfUri = uri
            if (uri != null) {
                aiViewModel.summarizePdf(context, uri)
                Toast.makeText(context, "PDF file uploaded and will be processed", Toast.LENGTH_SHORT).show()
            }
        }
    )
    val audioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            audioUri = uri
            if (uri != null) {
                aiViewModel.transcribeAudio(context, uri)
                Toast.makeText(context, "Audio file uploaded and will be processed", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UploadButton(
            text = "Upload Pdf File",
            iconResId = R.drawable.ic_pdf,
            onClick = { pdfLauncher.launch("application/pdf") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        UploadButton(
            text = "Upload Audio File",
            iconResId = R.drawable.ic_audio,
            onClick = { audioLauncher.launch("audio/*") }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun AiModelScreenPreview() {
    AiModelScreen(aiViewModel = AiViewModel())
}


@Composable
fun UploadButton(text: String, iconResId: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = text,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(48.dp))
            Text(text, fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.FileUpload,
                contentDescription = "Upload",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
