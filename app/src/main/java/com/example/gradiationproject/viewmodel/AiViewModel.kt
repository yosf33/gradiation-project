package com.example.gradiationproject.viewmodel

import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradiationproject.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AiViewModel : ViewModel() {

    private val apiService = RetrofitInstance.api

    private val _summary = MutableStateFlow<String?>(null)

    private val _transcripts = MutableStateFlow<List<String>>(emptyList())

    fun summarizePdf(context: Context, fileUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(fileUri)
            val file = File(context.cacheDir, "temp_pdf_file.pdf")
            val outputStream = FileOutputStream(file)
            Log.d("AiViewModel", "Copying PDF content to temporary file.")
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            Log.d("AiViewModel", "Finished copying PDF content to temporary file.")
            val requestFile = file.asRequestBody("application/pdf".toMediaTypeOrNull())
            val multipartBody = MultipartBody.Part.createFormData("pdf", file.name, requestFile)

            Log.d("AiViewModel", "Starting to summarize PDF: $fileUri.")
            try {
                val response = apiService.summarizePdf(multipartBody).execute()
                if (response.isSuccessful) {
                    val result = response.body()
                    // TODO: have problem not saving the pdf to the local  
                    Log.d("AiViewModel", "PDF summarization successful: $result")
                    _summary.value = result?.summary

                    createPdf(context, result?.summary ?: "", "summary_result.pdf")
                } else {
                    Log.e("AiViewModel", "PDF summarization failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AiViewModel", "Exception during PDF summarization: $e")
            }
        }
    }

    fun transcribeAudio(context: Context, fileUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(fileUri)
            val file = File(context.cacheDir, "temp_audio_file.mp3")
            val outputStream = FileOutputStream(file)
            Log.d("AiViewModel", "Copying audio content to temporary file.")
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            Log.d("AiViewModel", "Finished copying audio content to temporary file.")
            val requestFile = file.asRequestBody("audio/mpeg".toMediaTypeOrNull())
            val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestFile)

            Log.d("AiViewModel", "Starting to transcribe audio: $fileUri.")
            try {
                val response = apiService.transcribeAudio(multipartBody).execute()
                if (response.isSuccessful) {
                    val result = response.body()
                    // TODO: why audio not be saved ? 
                    Log.d("AiViewModel", "Audio transcription successful: $result")
                    _transcripts.value = result?.transcripts ?: emptyList()

                    createPdf(context, result?.transcripts?.joinToString("\n") ?: "", "transcription_result.pdf")

                } else {
                    Log.e("AiViewModel", "Audio transcription failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AiViewModel", "Exception during audio transcription: $e")
            }
        }
    }



    fun openPdf(context: Context, file: File) {
        Log.d("AiViewModel", "Pdf package name: ${context.packageName}")
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    }


    private fun createPdf(context: Context, text: String, fileName: String) {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size in points (72 points per inch)
        val page = document.startPage(pageInfo)

        val canvas = page.canvas
        val paint = android.graphics.Paint()
        paint.textSize = 12f
        paint.isAntiAlias = true

        val xPosition = 10f
        var yPosition = 40f
        val maxWidth = pageInfo.pageWidth - 20f // 10 points padding on each side

        val lines = text.split("\n")
        for (line in lines) {
            var start = 0
            while (start < line.length) {
                val count = paint.breakText(line, start, line.length, true, maxWidth, null)
                canvas.drawText(line.substring(start, start + count), xPosition, yPosition, paint)
                yPosition += paint.descent() - paint.ascent()
                start += count
            }
        }

        document.finishPage(page)

        val file = File(context.getExternalFilesDir(null), fileName)
        try {
            document.writeTo(FileOutputStream(file))
            document.close()
            // Notify the user
            viewModelScope.launch(Dispatchers.Main) {
                Toast.makeText(context, "File saved as $fileName", Toast.LENGTH_LONG).show()
                openPdf(context, file)
            }
        } catch (e: IOException) {
            Log.e("AiViewModel", "Error saving PDF file: $e")
        }
    }

}


