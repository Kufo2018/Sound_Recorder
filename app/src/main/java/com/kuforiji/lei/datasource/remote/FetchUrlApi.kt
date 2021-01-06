package com.kuforiji.lei.datasource.remote

import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FetchUrlApi {
    @POST("fetch_audio_urls")
    suspend fun fetchAudioUrl(
        @Body fetchUrlsRequest: FetchUrlRequest
    ): List<FetchUrlResponse>
}