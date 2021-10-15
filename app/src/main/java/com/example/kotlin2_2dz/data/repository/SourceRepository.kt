package com.example.kotlin2_2dz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin2_2dz.data.network.apiservices.SourceApiService
import com.example.kotlin2_2dz.data.repository.pagingSource.SourceCountryUS
import com.example.kotlin2_2dz.model.Sources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SourceRepository @Inject constructor(private val service: SourceApiService) {

    fun fetchSourceCountryUs(): Flow<PagingData<Sources>> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false), pagingSourceFactory = {
            SourceCountryUS(service)
        }
        ).flow
    }
}