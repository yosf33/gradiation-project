package com.example.gradiationproject.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradiationproject.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class AiViewModel : ViewModel() {

    private val apiService = RetrofitInstance.api

    private val _summary = MutableStateFlow<String?>(null)
    val summary: StateFlow<String?> = _summary.asStateFlow()

    private val _transcripts = MutableStateFlow<List<String>>(emptyList())
    val transcripts: StateFlow<List<String>> = _transcripts.asStateFlow()

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
                    Log.d("AiViewModel", "PDF summarization successful: $result")
                    _summary.value = result?.summary
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
                    Log.d("AiViewModel", "Audio transcription successful: $result")
                    _transcripts.value = result?.transcripts ?: emptyList()
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

    fun openAudio(context: Context, file: File) {
        Log.d("AiViewModel", "Audio package name: ${context.packageName}")
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "audio/*")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    }
}

