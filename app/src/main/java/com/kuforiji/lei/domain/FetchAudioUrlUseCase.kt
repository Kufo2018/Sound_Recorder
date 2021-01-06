package com.kuforiji.lei.domain

import com.kuforiji.lei.core.BaseUseCase
import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import com.kuforiji.lei.datasource.repository.AppRepository
import javax.inject.Inject

class FetchAudioUrlUseCase @Inject constructor(private val appRepository: AppRepository) :
    BaseUseCase.PostUseCase<FetchUrlRequest, FetchUrlResponse> {


    override suspend fun postData(param: FetchUrlRequest): FetchUrlResponse =
        appRepository.fetchAudioUrl(param)
}