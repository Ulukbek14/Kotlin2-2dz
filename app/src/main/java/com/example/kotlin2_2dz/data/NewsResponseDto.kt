package com.example.kotlin2_2dz.data

import com.google.gson.annotations.SerializedName

data class NewsResponseDto<T>(

    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: String?,

    @SerializedName("articles")
    val articles: List<T>?,

    @SerializedName("sources")
    val sources: List<T>?
)