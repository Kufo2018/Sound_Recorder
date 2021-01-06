package com.kuforiji.lei.domain

import com.kuforiji.lei.core.BaseUseCase
import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import com.kuforiji.lei.datasource.repository.AppRepository
import javax.inject.Inject

class FetchAudioUrlUseCase @Inject constructor(private val appRepository: AppRepository) :
    BaseUseCase.GetUseCase<FetchUrlRequest, FetchUrlResponse> {


    override suspend fun getData(param: FetchUrlRequest): List<FetchUrlResponse> =
        appRepository.fetchAudioUrl(param)
}