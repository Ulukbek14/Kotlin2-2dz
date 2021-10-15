package com.example.kotlin2_2dz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin2_2dz.base.BaseRepository
import com.example.kotlin2_2dz.data.network.apiservices.TopHeadlinesApiService
import com.example.kotlin2_2dz.data.repository.pagingSource.TopHeadlinesPagingSource
import com.example.kotlin2_2dz.model.EverythingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinesRepository @Inject constructor(private val service: TopHeadlinesApiService) : BaseRepository() {

    fun fetchTopHeadlines(): Flow<PagingData<EverythingModel>> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false), pagingSourceFactory = {
            TopHeadlinesPagingSource(service)
        }
        ).flow
    }
}