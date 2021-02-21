package com.example.androidtest.data

import android.util.Log
import com.example.androidtest.data.model.Login
import com.example.androidtest.data.response.LoginResponse
import com.example.androidtest.data.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String) {
        Retrofit().getApi().login(Login(username, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.e("Response", response.code().toString())
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                }
            })
    }
}