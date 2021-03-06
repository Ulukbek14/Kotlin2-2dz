package com.example.kotlin2_2dz.domain.model

import com.example.kotlin2_2dz.base.IBaseDiffModel

data class EverythingModel(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    override val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?, ) : IBaseDiffModel

data class Source(
    var id: String?,
    var name: String?)

data class Sources(
    val id: String?,
    val name: String?,
    val description: String?,
    override val url: String?,
    val category: String?,
    val language: String?,
    val country: String?) : IBaseDiffModel