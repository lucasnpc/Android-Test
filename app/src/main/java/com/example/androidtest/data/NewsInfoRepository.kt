package com.example.androidtest.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidtest.data.response.NewsInfoResponse
import com.example.androidtest.data.retrofit.Client
import com.example.androidtest.ui.newsInfo.NewsInfoResult
import com.example.androidtest.ui.newsInfo.NewsInfoState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsInfoRepository {
    fun getDocument(
        id: String,
        token: String,
        _newsInfoState: MutableLiveData<NewsInfoState>,
        _newsInfoResult: MutableLiveData<NewsInfoResult>
    ) =
        Client().getApi().getDocument(id = id, token = "Bearer $token")
            .enqueue(object : Callback<ArrayList<NewsInfoResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<NewsInfoResponse>>,
                    response: Response<ArrayList<NewsInfoResponse>>
                ) {
                    if (response.code() != 201) {
                        _newsInfoState.value = NewsInfoState(isLoading = false)
                        _newsInfoResult.value = NewsInfoResult(failed = true)
                        return
                    }
                    _newsInfoState.value = NewsInfoState(isLoading = false)
                    _newsInfoResult.value =
                        NewsInfoResult(success = true, newsInfo = response.body()?.get(0))
                    Log.e("Body", _newsInfoResult.value!!.newsInfo.toString())
                }

                override fun onFailure(call: Call<ArrayList<NewsInfoResponse>>, t: Throwable) {
                    _newsInfoState.value = NewsInfoState(isLoading = false)
                    _newsInfoResult.value = NewsInfoResult(failed = true)
                    Log.e("Error", t.message.toString())
                }
            })

}