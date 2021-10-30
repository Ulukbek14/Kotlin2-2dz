package com.example.kotlin2_2dz.domain.repository

import com.example.kotlin2_2dz.common.resource.Resource
import com.example.kotlin2_2dz.domain.model.Sources
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {

    fun fetchSources(page: Int): Flow<Resource<List<Sources>?>>
}