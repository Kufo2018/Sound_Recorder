package com.kuforiji.lei.datasource.repository

import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse

interface AppRepository {

    suspend fun uploadAudioUrl(uploadUrlRequest: UploadUrlRequest): UploadUrlResponse

    suspend fun fetchAudioUrl(fetchUrlRequest: FetchUrlRequest): List<FetchUrlResponse>
}