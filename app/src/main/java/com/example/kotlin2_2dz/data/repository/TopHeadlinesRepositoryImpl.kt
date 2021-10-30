package com.example.kotlin2_2dz.data.repository

import com.example.kotlin2_2dz.base.BaseRepository
import com.example.kotlin2_2dz.data.dtos.toArticles
import com.example.kotlin2_2dz.data.dtos.toNewsResponse
import com.example.kotlin2_2dz.data.network.apiservices.TopHeadlinesApiService
import com.example.kotlin2_2dz.domain.repository.TopHeadLinesRepository
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(private val service: TopHeadlinesApiService) : BaseRepository(), TopHeadLinesRepository {

    override fun fetchTopHeadlines(page: Int) = doRequest {
        service.fetchTopHeadlines("us", page).toNewsResponse().articles?.map { it.toArticles() }
    }
}