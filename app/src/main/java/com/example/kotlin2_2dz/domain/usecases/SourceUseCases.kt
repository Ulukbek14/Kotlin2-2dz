package com.example.kotlin2_2dz.domain.usecases

import com.example.kotlin2_2dz.domain.repository.SourcesRepository
import javax.inject.Inject

class SourceUseCases @Inject constructor(private val repository: SourcesRepository) {

    operator fun invoke(page: Int) = repository.fetchSources(page)
}