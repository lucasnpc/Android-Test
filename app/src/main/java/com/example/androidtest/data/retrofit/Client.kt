package com.example.androidtest.data.retrofit

import com.example.androidtest.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {

    fun getApi(): ApiInterface {
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(okhttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ApiInterface::class.java)
    }

    private fun okhttpClient() = OkHttpClient.Builder()
            .build()
}