package com.example.androidtest.ui.newsList

import com.example.androidtest.data.model.News

data class NewsListResult(
    val success: Boolean? = false,
    val failed: Boolean? = false,
    val news: ArrayList<News>? = null
)
