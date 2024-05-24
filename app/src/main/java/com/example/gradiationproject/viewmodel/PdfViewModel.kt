package com.example.gradiationproject.viewmodel
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradiationproject.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PdfViewModel : ViewModel() {

    private val apiService = RetrofitInstance.api

    private fun downloadFile(context: Context, fileName: String, saveAs: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.downloadFile(fileName).execute()
            if (response.isSuccessful) {
                val inputStream = response.body()?.byteStream()
                val file = File(context.cacheDir, saveAs)
                val outputStream = FileOutputStream(file)
                inputStream?.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                Log.d("PdfViewModel", "Downloaded file: ${file.name}")
                // Handle the downloaded file (e.g., open it, display it, etc.)
            } else {
                Log.e("PdfViewModel", "Failed to download file: ${response.message()}")
            }
        }
    }


    fun splitPdf(context: Context, fileUri: Uri, numParts: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(fileUri)
            val file = File(context.cacheDir, "temp_pdf_file.pdf")
            val outputStream = FileOutputStream(file)
            Log.d("PdfViewModel", "Copying PDF content to temporary file.")
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            Log.d("PdfViewModel", "Finished copying PDF content to temporary file.")
            val requestFile = file.asRequestBody("application/pdf".toMediaTypeOrNull())
            val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestFile)

            Log.d("PdfViewModel", "Starting to split PDF: $fileUri into $numParts parts.")
            try {
                val response = RetrofitInstance.api.splitPdf(multipartBody, numParts.toInt()).execute()
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.d("PdfViewModel", "PDF split successful: $result")
                    result?.files?.forEachIndexed { index, fileUrl ->
                        Log.d("PdfViewModel", "Downloading PDF part: $fileUrl")
                        downloadFile(context, "part_${index + 1}.pdf", "part_${index + 1}.pdf")

                    }
                } else {
                    // Handle the error
                    Log.e("PdfViewModel", "PDF split failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Handle the exception
                Log.e("PdfViewModel", "Exception during PDF split: $e")
            }


        }
    }
}

