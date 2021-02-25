package com.example.androidtest

import com.example.androidtest.data.response.NewsInfoResponse
import com.example.androidtest.data.retrofit.Client
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsInfoUnitTest {
    private val token = "jjJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.0IEYC9TrL0FfQLhfE8Sp8DnDcv2xrJLUADIM75xUSPw"

    @Before
    fun setUpMockito(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getResultNewsInfoSuccess(){
        val client = Client().getApi()

        val call = client.getNewsInfo("1", token)

        val spy = spy(call)

        spy.enqueue(object : Callback<ArrayList<NewsInfoResponse>>{
            override fun onResponse(
                call: Call<ArrayList<NewsInfoResponse>>,
                response: Response<ArrayList<NewsInfoResponse>>
            ) {
                assert(response.body() != null)
            }

            override fun onFailure(call: Call<ArrayList<NewsInfoResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    @Test
    fun getResultNewsInfoFailed(){
        val client = Client().getApi()

        val call = client.getNewsInfo("4", token)

        val spy = spy(call)

        spy.enqueue(object : Callback<ArrayList<NewsInfoResponse>>{
            override fun onResponse(
                call: Call<ArrayList<NewsInfoResponse>>,
                response: Response<ArrayList<NewsInfoResponse>>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ArrayList<NewsInfoResponse>>, t: Throwable) {
                assert(t.message != "")
            }

        })
    }
}