package com.fongfuse.githubdemo.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean?,
    @SerializedName("items")
    val items: List<BaseResponse>?,
    @SerializedName("total_count")
    val total_count: Int?
)