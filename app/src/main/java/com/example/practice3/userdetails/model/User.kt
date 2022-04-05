package com.example.practice3.userdetails.model

import com.google.gson.annotations.SerializedName

class User constructor(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val username: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("gender") val gender: String = ""
)
