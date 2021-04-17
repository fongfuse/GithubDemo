package com.fongfuse.githubdemo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    open fun showLoading() {
        _loading.value = true
    }

    open fun hideLoading() {
        _loading.value = false
    }

}