package com.example.kotlin2_2dz.data.repository

import com.example.kotlin2_2dz.base.BaseRepository
import com.example.kotlin2_2dz.data.dtos.toArticles
import com.example.kotlin2_2dz.data.dtos.toNewsResponse
import com.example.kotlin2_2dz.data.network.apiservices.EverythingApiService
import com.example.kotlin2_2dz.domain.repository.EverythingRepository
import javax.inject.Inject

class EverythingRepositoryImpl @Inject constructor(private val service: EverythingApiService) : BaseRepository(),
    EverythingRepository {

    override fun fetchEverything(page: Int) = doRequest {
        service.fetchEverything("bitcoin", page).toNewsResponse().articles?.map { it.toArticles() }
    }
}