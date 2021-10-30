package com.example.kotlin2_2dz.domain.usecases

import com.example.kotlin2_2dz.domain.repository.TopHeadLinesRepository
import javax.inject.Inject

class TopHeadLinesUseCases @Inject constructor(private val repository: TopHeadLinesRepository) {

    operator fun invoke(page: Int) = repository.fetchTopHeadlines(page)
}