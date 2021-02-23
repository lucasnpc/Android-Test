package com.example.androidtest.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidtest.R
import com.example.androidtest.data.model.Login
import com.example.androidtest.data.response.LoginResponse
import com.example.androidtest.data.retrofit.Client
import com.example.androidtest.ui.login.LoginFormState
import com.example.androidtest.ui.login.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    var login: Login? = null
        private set

    init {
        login = null
    }

    fun login(
        username: String,
        password: String,
        _loginResult: MutableLiveData<LoginResult>,
        _loginState: MutableLiveData<LoginFormState>
    ) =
        Client().getApi().login(Login(username, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.e("Code", response.code().toString())
                    if (response.code() != 200) {
                        _loginResult.value =
                            LoginResult(error = R.string.login_failed)
                        _loginState.value = LoginFormState(isLoading = false)
                        return
                    }
                    Log.e("Token", response.body()?.token.toString())
                    _loginResult.value =
                        LoginResult(success = true, token = response.body()?.token.toString())
                    _loginState.value = LoginFormState(isLoading = false)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _loginResult.value =
                        LoginResult(error = R.string.login_failed)
                    _loginState.value = LoginFormState(isLoading = false)
                }
            })
}