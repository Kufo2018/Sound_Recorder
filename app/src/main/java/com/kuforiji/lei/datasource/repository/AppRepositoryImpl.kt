package com.kuforiji.lei.datasource.repository

import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse
import com.kuforiji.lei.datasource.remote.RemoteUploadUrlsRequest
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteFetchUrlsRequest: RemoteUploadUrlsRequest
) : AppRepository {

    override suspend fun uploadAudioUrl(uploadUrlRequest: UploadUrlRequest): UploadUrlResponse =
        remoteFetchUrlsRequest.uploadAudioUrl(uploadUrlRequest)
}