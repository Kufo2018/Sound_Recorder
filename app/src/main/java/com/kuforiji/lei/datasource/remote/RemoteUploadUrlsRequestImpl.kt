package com.kuforiji.lei.datasource.remote

import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse
import javax.inject.Inject

class RemoteUploadUrlsRequestImpl @Inject constructor(private val uploadUrlApi: UploadUrlApi) :
    RemoteUploadUrlsRequest {

    override suspend fun uploadAudioUrl(uploadUrlRequest: UploadUrlRequest): UploadUrlResponse =
        uploadUrlApi.postAudioUrl(uploadUrlRequest)
}