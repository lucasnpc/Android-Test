package com.example.androidtest.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("erro")
    var message: String,
    @SerializedName("token")
    var token: String
        )