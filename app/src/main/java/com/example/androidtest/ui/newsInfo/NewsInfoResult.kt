package com.example.androidtest.ui.newsInfo

import com.example.androidtest.data.response.NewsInfoResponse

data class NewsInfoResult(
    val success: Boolean? = false,
    val failed: Boolean? = false,
    val newsInfo: NewsInfoResponse? = null
)