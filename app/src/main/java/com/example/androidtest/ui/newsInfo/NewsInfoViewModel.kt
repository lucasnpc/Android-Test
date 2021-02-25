package com.example.androidtest.ui.newsInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.data.NewsInfoRepository

class NewsInfoViewModel(private val newsInfoRepository: NewsInfoRepository): ViewModel() {
    private val _documentState = MutableLiveData<NewsInfoState>()
    val newsInfoState: LiveData<NewsInfoState> = _documentState

    private val _documentResult = MutableLiveData<NewsInfoResult>()
    val newsInfoResult: LiveData<NewsInfoResult> = _documentResult

    fun getDocument(id: String, token: String){
        _documentState.value = NewsInfoState(isLoading = true)
        newsInfoRepository.getNewsInfo(id,token, _documentState, _documentResult)
    }
}