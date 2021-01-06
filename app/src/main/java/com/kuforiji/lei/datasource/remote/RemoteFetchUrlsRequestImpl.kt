package com.kuforiji.lei.datasource.remote

import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import javax.inject.Inject

class RemoteFetchUrlsRequestImpl @Inject constructor(private val fetchUrlApi: FetchUrlApi) :
    RemoteFetchUrlsRequest {
    override suspend fun fetchUrl(fetchUrlsRequest: FetchUrlRequest): List<FetchUrlResponse> =
        fetchUrlApi.fetchAudioUrl(fetchUrlsRequest = fetchUrlsRequest)
}