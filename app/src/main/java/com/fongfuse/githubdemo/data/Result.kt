package com.fongfuse.githubdemo.data

import kotlinx.coroutines.flow.Flow

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val throwable: Throwable) : Result<T>()
    data class Loading<T>(val isLoading: Boolean) : Result<T>()
}

