package com.example.androidtest.data.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("user")
    val username: String,
    @SerializedName("pass")
    val password: String
)