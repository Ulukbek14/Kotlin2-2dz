package com.example.kotlin2_2dz.ui.fragment.topheadlines

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin2_2dz.base.BaseViewModel
import com.example.kotlin2_2dz.data.repository.TopHeadlinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(private val repository: TopHeadlinesRepository) :
    BaseViewModel() {

    fun fetchTopHeadlines() = repository.fetchTopHeadlines().cachedIn(viewModelScope)
}