package com.example.androidtest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.androidtest.data.LoginRepository

import com.example.androidtest.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginState

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        _loginState.value = LoginFormState(isLoading = true)
        loginRepository.login(username, password, _loginResult, _loginState)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginState.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginState.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginState.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String) = if (username.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
        username.isNotBlank()
    }


    private fun isPasswordValid(password: String) = password.length > 5

}