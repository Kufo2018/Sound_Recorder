package com.kuforiji.lei.datasource.remote

import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse

interface RemoteFetchUrlsRequest {

    suspend fun fetchUrl(fetchUrlsRequest: FetchUrlRequest): List<FetchUrlResponse>
}