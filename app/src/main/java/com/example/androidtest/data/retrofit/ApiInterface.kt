package com.example.androidtest.data.retrofit

import com.example.androidtest.data.model.Login
import com.example.androidtest.data.model.News
import com.example.androidtest.data.response.LoginResponse
import com.example.androidtest.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-type: application/json")
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: Login): Call<LoginResponse>

    @GET(Constants.NEWS_URL)
    fun getNews(): Call<News>
}