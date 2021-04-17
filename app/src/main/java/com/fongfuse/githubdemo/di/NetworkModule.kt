package com.fongfuse.githubdemo.di

import android.content.Context
import com.fongfuse.githubdemo.R
import com.fongfuse.githubdemo.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
}

val serviceModule = module {
    single { get<Retrofit>().create(ApiService::class.java) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

private fun provideRetrofit(context: Context,
                            okHttpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(context.getString(R.string.api_url))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
