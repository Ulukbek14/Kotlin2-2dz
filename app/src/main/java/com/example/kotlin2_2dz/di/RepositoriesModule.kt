package com.example.kotlin2_2dz.di

import com.example.kotlin2_2dz.data.network.apiservices.EverythingApiService
import com.example.kotlin2_2dz.data.network.apiservices.SourceApiService
import com.example.kotlin2_2dz.data.network.apiservices.TopHeadlinesApiService
import com.example.kotlin2_2dz.data.repository.EverythingRepositoryImpl
import com.example.kotlin2_2dz.data.repository.SourcesRepositoryImpl
import com.example.kotlin2_2dz.data.repository.TopHeadlinesRepositoryImpl
import com.example.kotlin2_2dz.domain.repository.EverythingRepository
import com.example.kotlin2_2dz.domain.repository.SourcesRepository
import com.example.kotlin2_2dz.domain.repository.TopHeadLinesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideEverythingRepository(service: EverythingApiService): EverythingRepository =
        EverythingRepositoryImpl(service)

    @Provides
    fun provideTopHeadlinesRepository(service: TopHeadlinesApiService): TopHeadLinesRepository =
        TopHeadlinesRepositoryImpl(service)

    @Provides
    fun provideSourcesRepository(service: SourceApiService): SourcesRepository =
        SourcesRepositoryImpl(service)
}