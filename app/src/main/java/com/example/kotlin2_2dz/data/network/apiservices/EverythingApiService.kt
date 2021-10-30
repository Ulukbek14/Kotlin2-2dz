package com.example.kotlin2_2dz.data.network.apiservices

import com.example.kotlin2_2dz.data.dtos.model.EverythingModelDto
import com.example.kotlin2_2dz.data.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EverythingApiService {

    @GET("/v2/everything?apiKey=ebe66846f18e453885e497d3f5015df5")
    suspend fun fetchEverything(
        @Query("q") query: String,
        @Query("page") page: Int
    ): NewsResponseDto<EverythingModelDto>
}