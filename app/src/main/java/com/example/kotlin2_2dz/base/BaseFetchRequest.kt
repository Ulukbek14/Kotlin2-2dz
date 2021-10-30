package com.example.kotlin2_2dz.base

interface BaseFetchRequest { var page: Int
    fun fetchNews(page: Int)
}