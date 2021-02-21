package com.example.androidtest.data

import com.example.androidtest.data.model.Login
import com.example.androidtest.data.response.LoginResponse

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var login: Login? = null
        private set

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        login = null
    }

    fun login(username: String, password: String) {
        dataSource.login(username, password)
    }

}