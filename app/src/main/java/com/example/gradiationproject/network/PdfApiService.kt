package com.example.gradiationproject.network

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PdfApiService {
    @Multipart
    @POST("split_pdf")
    fun splitPdf(
        @Part file: MultipartBody.Part,
        @Part("num_parts") numParts: Int
    ): Call<SplitPdfResponse>
}

data class SplitPdfResponse (
    val files: List<String>,
    val message: String
    )
