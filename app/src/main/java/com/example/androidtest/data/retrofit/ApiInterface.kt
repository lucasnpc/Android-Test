package com.example.androidtest.data.retrofit

import com.example.androidtest.data.model.Login
import com.example.androidtest.data.model.News
import com.example.androidtest.data.response.LoginResponse
import com.example.androidtest.data.response.NewsInfoResponse
import com.example.androidtest.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-type: application/json")
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: Login): Call<LoginResponse>

    @GET(Constants.NEWS_LIST_URL)
    fun getNews(@Header("Authorization") token: String): Call<ArrayList<News>>

    @GET(Constants.NEWS_INFO_URL)
    fun getDocument(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Call<ArrayList<NewsInfoResponse>>
}