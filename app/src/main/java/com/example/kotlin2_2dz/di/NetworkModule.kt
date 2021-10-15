package com.example.kotlin2_2dz.di

import com.example.kotlin2_2dz.data.network.RetrofitClient
import com.example.kotlin2_2dz.data.network.apiservices.EverythingApiService
import com.example.kotlin2_2dz.data.network.apiservices.SourceApiService
import com.example.kotlin2_2dz.data.network.apiservices.TopHeadlinesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofit: RetrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun fetchEverything(): EverythingApiService {
        return retrofit.provideEverythingApiService()
    }

    @Singleton
    @Provides
    fun fetchTopHeadlines(): TopHeadlinesApiService {
        return retrofit.provideTopHeadlinesApiService()
    }

    @Singleton
    @Provides
    fun fetchSources(): SourceApiService {
        return retrofit.provideSourcesApiService()
    }
}