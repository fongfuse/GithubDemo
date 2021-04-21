package com.fongfuse.githubdemo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fongfuse.githubdemo.base.BaseViewModel
import com.fongfuse.githubdemo.data.BaseResponse
import com.fongfuse.githubdemo.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: GithubRepository) : BaseViewModel() {

    private val _items = MutableLiveData<List<BaseResponse>>()
    val items: LiveData<List<BaseResponse>> = _items

    fun callSearchUsers(search: String) {
        viewModelScope.launch {
            repository.search(search)
                .flowOn(Dispatchers.IO)
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { println("catch :: " + it.message) }
                .collect {
                    println("collect")
                    _items.value = it.items ?: emptyList()
                }
        }

    }

    fun itemsIsEmpty(): Boolean = (_items.value.isNullOrEmpty())

}