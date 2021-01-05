package com.kuforiji.lei.datasource.remote

import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UploadUrlApi {
    @POST("save_audio_files")
    suspend fun postAudioUrl(
        @Body uploadUrlRequest: UploadUrlRequest
    ): UploadUrlResponse
}