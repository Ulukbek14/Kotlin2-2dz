package com.example.kotlin2_2dz.domain.usecases

import com.example.kotlin2_2dz.domain.repository.EverythingRepository
import javax.inject.Inject

class EverythingUseCases @Inject constructor(private val repository: EverythingRepository) {

    operator fun invoke(page: Int) = repository.fetchEverything(page)
}