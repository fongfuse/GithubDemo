package com.fongfuse.githubdemo.data

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String
)
