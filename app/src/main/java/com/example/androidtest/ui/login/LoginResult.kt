package com.example.androidtest.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
        val success: Boolean? = false,
        val error: Int? = null,
        val token: String = ""
)