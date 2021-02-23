package com.example.androidtest.ui.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.data.NewsListRepository
import java.lang.IllegalArgumentException

class NewsListViewlModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(newsListRepository = NewsListRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}