package com.example.androidtest

import com.example.androidtest.data.model.Login
import com.example.androidtest.data.response.LoginResponse
import com.example.androidtest.data.retrofit.Client
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUnitTest {

    @Before
    fun setUpMockito(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loginIsGettingToken(){
        val client = Client().getApi()

        val call = client.login(Login(username = "devmobile", password = "uC&+}H4wg?rYbF:"))

        val spy = spy(call)

        spy.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                assert(response.body()?.token != null)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    @Test
    fun loginIsntGettingToken(){
        val client = Client().getApi()

        val call = client.login(Login(username = "devmobile", password = "1234"))

        val spy = spy(call)

        spy.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                assert(t.message != "")
            }

        })
    }
}