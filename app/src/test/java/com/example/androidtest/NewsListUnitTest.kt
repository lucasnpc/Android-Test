package com.example.androidtest

import com.example.androidtest.data.model.News
import com.example.androidtest.data.retrofit.Client
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListUnitTest {

    val token =
        "jjJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.0IEYC9TrL0FfQLhfE8Sp8DnDcv2xrJLUADIM75xUSPw"

    @Before
    fun setUpMockito() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getResultNewsSuccess() {
        val client = Client().getApi()

        val call = client.getNews(token = token)

        val spy = spy(call)

        spy.enqueue(object : Callback<ArrayList<News>> {
            override fun onResponse(
                call: Call<ArrayList<News>>,
                response: Response<ArrayList<News>>
            ) {
                assert(response.body() != null)
            }

            override fun onFailure(call: Call<ArrayList<News>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    @Test
    fun getResultNewsFailed() {
        val client = Client().getApi()

        val call = client.getNews(token = "123")

        val spy = spy(call)

        spy.enqueue(object : Callback<ArrayList<News>>
        {
            override fun onResponse(
                call: Call<ArrayList<News>>,
                response: Response<ArrayList<News>>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ArrayList<News>>, t: Throwable) {
                assert(t.message != "")
            }

        })
    }
}
