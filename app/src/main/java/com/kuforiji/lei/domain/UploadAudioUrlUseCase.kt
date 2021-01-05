package com.kuforiji.lei.domain

import com.kuforiji.lei.core.BaseUseCase
import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse
import com.kuforiji.lei.datasource.repository.AppRepository
import javax.inject.Inject

class UploadAudioUrlUseCase @Inject constructor(private val appRepository: AppRepository) :
    BaseUseCase
    .PostUseCase<UploadUrlRequest, UploadUrlResponse> {

    override suspend fun postData(param: UploadUrlRequest): UploadUrlResponse =
        appRepository.uploadAudioUrl(param)

}