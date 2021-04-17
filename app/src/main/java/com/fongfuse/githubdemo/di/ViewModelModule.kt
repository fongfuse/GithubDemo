package com.fongfuse.githubdemo.di

import com.fongfuse.githubdemo.main.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}