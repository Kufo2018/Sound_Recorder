package com.kuforiji.lei.datasource.repository

import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse
import com.kuforiji.lei.datasource.remote.RemoteFetchUrlsRequest
import com.kuforiji.lei.datasource.remote.RemoteUploadUrlsRequest
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteUploadUrlsRequest: RemoteUploadUrlsRequest,
    private val remoteFetchUrlsRequest: RemoteFetchUrlsRequest
) : AppRepository {

    override suspend fun uploadAudioUrl(uploadUrlRequest: UploadUrlRequest): UploadUrlResponse =
        remoteUploadUrlsRequest.uploadAudioUrl(uploadUrlRequest)

    override suspend fun fetchAudioUrl(fetchUrlRequest: FetchUrlRequest): List<FetchUrlResponse> =
        remoteFetchUrlsRequest.fetchUrl(fetchUrlRequest)
}