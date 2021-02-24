package com.example.androidtest.data.response

import com.example.androidtest.data.model.NewsInfo
import com.google.gson.annotations.SerializedName

data class NewsInfoResponse(
    @SerializedName("documento")
    var newsInfo: NewsInfo
)
