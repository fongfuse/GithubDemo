package com.fongfuse.githubdemo.di

import com.fongfuse.githubdemo.repository.GithubRepository
import com.fongfuse.githubdemo.repository.GithubRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<GithubRepository> { GithubRepositoryImpl(get()) }
}