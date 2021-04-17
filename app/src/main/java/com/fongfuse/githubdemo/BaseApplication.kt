package com.fongfuse.githubdemo

import android.app.Application
import com.fongfuse.githubdemo.di.networkModule
import com.fongfuse.githubdemo.di.repositoryModule
import com.fongfuse.githubdemo.di.serviceModule
import com.fongfuse.githubdemo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(getModules())
        }
    }

    private fun getModules() =
        listOf(
            networkModule,
            serviceModule,
            repositoryModule,
            viewModelModule
        )
}