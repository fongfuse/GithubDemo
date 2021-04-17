package com.fongfuse.githubdemo.service

import com.fongfuse.githubdemo.data.SearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") search: String)
    : SearchResponse
}