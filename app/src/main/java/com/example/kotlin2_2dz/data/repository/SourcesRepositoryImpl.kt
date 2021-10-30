package com.example.kotlin2_2dz.data.repository

import com.example.kotlin2_2dz.base.BaseRepository
import com.example.kotlin2_2dz.data.dtos.toNewsResponse
import com.example.kotlin2_2dz.data.dtos.toSources
import com.example.kotlin2_2dz.data.network.apiservices.SourceApiService
import com.example.kotlin2_2dz.domain.repository.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val service: SourceApiService) :
    BaseRepository(), SourcesRepository {

    override fun fetchSources(page: Int) = doRequest {
        service.fetchSourcesCountryUs("us", 1).toNewsResponse().sources?.map { it.toSources() }
    }
}