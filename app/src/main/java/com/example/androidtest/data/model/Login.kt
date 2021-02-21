package com.example.androidtest.data.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("user")
    val userId: String,
    @SerializedName("pass")
    val password: String
)