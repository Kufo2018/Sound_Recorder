package com.kuforiji.lei.datasource.repository

import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse

interface AppRepository {

    suspend fun uploadAudioUrl(uploadUrlRequest: UploadUrlRequest): UploadUrlResponse
}