package com.example.androidtest.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidtest.data.model.News
import com.example.androidtest.data.retrofit.Client
import com.example.androidtest.ui.newsList.NewsListResult
import com.example.androidtest.ui.newsList.NewsListState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListRepository {
    fun getNews(
        token: String,
        result: MutableLiveData<NewsListResult>,
        state: MutableLiveData<NewsListState>
    ) =
        Client().getApi().getNews(token = "Bearer ${token}").enqueue(object : Callback<ArrayList<News>> {
            override fun onResponse(call: Call<ArrayList<News>>, response: Response<ArrayList<News>>) {
                if (response.code() != 201) {
                    result.value = NewsListResult(failed = true)
                    state.value = NewsListState(isLoading = false)
                    Log.e("Code", response.code().toString())
                    return
                }
                result.value = NewsListResult(success = true)
                state.value = NewsListState(isLoading = false)

                Log.e("Body", response.body().toString())
                result.value = NewsListResult(news = response.body())
            }

            override fun onFailure(call: Call<ArrayList<News>>, t: Throwable) {
                result.value = NewsListResult(failed = true)
                state.value = NewsListState(isLoading = false)
                Log.e("Failed", t.message.toString())
            }

        })

}