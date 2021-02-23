package com.example.androidtest

import androidx.lifecycle.MutableLiveData
import com.example.androidtest.data.NewsListRepository
import com.example.androidtest.ui.newsList.NewsListResult
import com.example.androidtest.ui.newsList.NewsListState
import org.junit.Assert.*
import org.junit.Test

class NewsListUnitTest {
    private val _newsListState = MutableLiveData<NewsListState>()

    private val _newsListResult = MutableLiveData<NewsListResult>()

    private val newsListRepository = NewsListRepository()

    val token = "jjJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.0IEYC9TrL0FfQLhfE8Sp8DnDcv2xrJLUADIM75xUSPw"

    @Test
    fun getNews() {
        newsListRepository.getNews(token, _newsListResult, _newsListState)
        assertEquals(null ,_newsListResult.value?.news)
    }
}