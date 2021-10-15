package com.example.kotlin2_2dz.data.network.apiservices

import com.example.kotlin2_2dz.model.NewsResponse
import com.example.kotlin2_2dz.model.Sources
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceApiService {

    @GET("/v2/top-headlines/sources?apiKey=cee5ca142bd84c8b8975076757814681")
    suspend fun fetchSourcesCountryUs(
        @Query("country") query: String,
        @Query("page") page: Int): NewsResponse<Sources>
}