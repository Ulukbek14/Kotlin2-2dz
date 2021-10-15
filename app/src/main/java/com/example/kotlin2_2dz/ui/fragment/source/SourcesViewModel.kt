package com.example.kotlin2_2dz.ui.fragment.source

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin2_2dz.base.BaseViewModel
import com.example.kotlin2_2dz.data.repository.SourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val repository: SourceRepository) :
    BaseViewModel() {

    fun fetchSourcesCountryUs() = repository.fetchSourceCountryUs().cachedIn(viewModelScope)
}