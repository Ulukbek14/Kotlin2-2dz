package com.example.kotlin2_2dz.domain.repository

import com.example.kotlin2_2dz.common.resource.Resource
import com.example.kotlin2_2dz.domain.model.EverythingModel
import kotlinx.coroutines.flow.Flow

interface TopHeadLinesRepository {

    fun fetchTopHeadlines(page: Int): Flow<Resource<List<EverythingModel>?>>
}