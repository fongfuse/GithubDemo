package com.fongfuse.githubdemo.repository

import com.fongfuse.githubdemo.data.SearchResponse
import com.fongfuse.githubdemo.service.ApiService
import kotlinx.coroutines.flow.*

interface GithubRepository {
    suspend fun search(search: String): Flow<SearchResponse>
}

class GithubRepositoryImpl constructor(private val api: ApiService) : GithubRepository {

    override suspend fun search(search: String): Flow<SearchResponse> {
        return flow {
            val fooList = api.searchUsers(search)
            emit(fooList)
        }
    }

}