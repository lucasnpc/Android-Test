package com.example.androidtest.ui.newsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.data.NewsListRepository
import okhttp3.internal.wait

class NewsListViewModel(private val newsListRepository: NewsListRepository) : ViewModel() {


    private val _newsListState = MutableLiveData<NewsListState>()
    val newsListState: LiveData<NewsListState> = _newsListState

    private val _newsListResult = MutableLiveData<NewsListResult>()
    val newsListResult: LiveData<NewsListResult> = _newsListResult

    fun getNews(token: String) {
        _newsListState.value = NewsListState(isLoading = true)
        newsListRepository.getNews(token, _newsListResult, _newsListState)
    }
}