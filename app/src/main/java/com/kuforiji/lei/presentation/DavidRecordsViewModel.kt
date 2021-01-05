package com.kuforiji.lei.presentation

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuforiji.lei.core.BaseUseCase
import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val LOG = "AudioRecordLog"

class DavidRecordsViewModel @ViewModelInject constructor(
    private val uploadUrlsUseCase: BaseUseCase.PostUseCase<UploadUrlRequest, UploadUrlResponse>
) : ViewModel() {

    private var _uploadAudioLiveData = MutableLiveData<UploadUrlResponse>()
    val uploadAudioLiveData: LiveData<UploadUrlResponse> = _uploadAudioLiveData


    fun uploadUrl(fileName: String, url: String) {
        val uploadUrlRequest = UploadUrlRequest(fileName, url)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    uploadUrlsUseCase.postData(uploadUrlRequest)
                }.onSuccess {
                    Log.i(LOG, "successfully uploaded url and response is ${it.fileName}")
                    _uploadAudioLiveData.postValue(it)
                }
                    .onFailure {
                        Log.i(LOG, "failed to upload URL ${it.message}")
                    }
            }
        }
    }

}