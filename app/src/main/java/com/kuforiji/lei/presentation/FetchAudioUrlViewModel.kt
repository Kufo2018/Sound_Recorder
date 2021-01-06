package com.kuforiji.lei.presentation

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuforiji.lei.core.BaseUseCase
import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FetchAudioUrlViewModel @ViewModelInject constructor(
    private val fetchAudioUrlUseCase:
    BaseUseCase.GetUseCase<FetchUrlRequest, FetchUrlResponse>
) : ViewModel() {

    private var _fetchAudioLiveData = MutableLiveData<List<FetchUrlResponse>>()
    val fetchAudioLiveData: LiveData<List<FetchUrlResponse>> = _fetchAudioLiveData

    fun fetchAudioUrls(name: String) {
        val fetchUrlRequest = FetchUrlRequest(name)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    fetchAudioUrlUseCase.getData(fetchUrlRequest)
                }
                    .onSuccess {
                        Log.i(LOG, "fetched urls successfully")
                        _fetchAudioLiveData.postValue(it)
                    }
                    .onFailure {
                        Log.i(LOG, "failed to fetch URL ${it.message}")
                    }
            }
        }
    }
}