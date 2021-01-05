package com.kuforiji.lei.datasource.remote

import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse

interface RemoteUploadUrlsRequest {
    suspend fun uploadAudioUrl(uploadUrlRequest: UploadUrlRequest): UploadUrlResponse
}