package com.example.kotlin2_2dz.data.dtos

import com.example.kotlin2_2dz.data.dtos.model.EverythingModelDto
import com.example.kotlin2_2dz.data.NewsResponseDto
import com.example.kotlin2_2dz.data.dtos.model.SourceDto
import com.example.kotlin2_2dz.data.dtos.model.SourcesDto
import com.example.kotlin2_2dz.domain.model.EverythingModel
import com.example.kotlin2_2dz.domain.model.NewsResponse
import com.example.kotlin2_2dz.domain.model.Source
import com.example.kotlin2_2dz.domain.model.Sources

fun <T> NewsResponseDto<T>.toNewsResponse() = NewsResponse<T>(
    status, totalResults, articles, sources
)

fun EverythingModelDto.toArticles() =
    EverythingModel(
        source?.toSource(),
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt,
        content
    )

fun SourceDto.toSource() = Source(id, name)

fun SourcesDto.toSources() = Sources(id, name, description, url, category, language, country)