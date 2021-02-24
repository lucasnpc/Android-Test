package com.example.androidtest.ui.newsInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.data.NewsInfoRepository
import java.lang.IllegalArgumentException

class NewsInfoViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsInfoViewModel::class.java)) {
            return NewsInfoViewModel(newsInfoRepository = NewsInfoRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewlModel Class")
    }
}