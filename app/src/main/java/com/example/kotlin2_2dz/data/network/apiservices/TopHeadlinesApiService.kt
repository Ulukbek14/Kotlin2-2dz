package com.example.kotlin2_2dz.data.network.apiservices

import com.example.kotlin2_2dz.model.EverythingModel
import com.example.kotlin2_2dz.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesApiService {

    @GET("/v2/top-headlines?apiKey=cee5ca142bd84c8b8975076757814681")
    suspend fun fetchTopHeadlines(
        @Query("country") query: String,
        @Query("page") page: Int): NewsResponse<EverythingModel>
}