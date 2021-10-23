package com.example.kotlin2_2dz.domain.model


data class NewsResponse<T>(
    val status: String?,
    val totalResults: String?,
    val articles: List<T>?,
    val sources: List<T>?
)