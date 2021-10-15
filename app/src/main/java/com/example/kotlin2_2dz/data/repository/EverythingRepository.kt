package com.example.kotlin2_2dz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin2_2dz.base.BaseRepository
import com.example.kotlin2_2dz.data.network.apiservices.EverythingApiService
import com.example.kotlin2_2dz.data.repository.pagingSource.EverythingPagingSource
import com.example.kotlin2_2dz.model.EverythingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EverythingRepository @Inject constructor(private val service: EverythingApiService) : BaseRepository() {

    fun fetchEverything(): Flow<PagingData<EverythingModel>> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false), pagingSourceFactory = {
            EverythingPagingSource(service)
        }
        ).flow
    }
}