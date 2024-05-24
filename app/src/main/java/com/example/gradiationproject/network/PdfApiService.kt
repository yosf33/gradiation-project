package com.example.gradiationproject.network

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Streaming

interface PdfApiService {
    @Multipart
    @POST("split_pdf")
    fun splitPdf(
        @Part file: MultipartBody.Part,
        @Part("num_parts") numParts: Int
    ): Call<SplitPdfResponse>

    @Streaming
    @GET("download/{filename}")
    fun downloadFile(@Path("filename") filename: String): Call<ResponseBody>
}

data class SplitPdfResponse (
    val files: List<String>,
    val message: String
    )
