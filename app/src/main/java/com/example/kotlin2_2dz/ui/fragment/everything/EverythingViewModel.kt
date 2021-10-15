package com.example.kotlin2_2dz.ui.fragment.everything

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin2_2dz.base.BaseViewModel
import com.example.kotlin2_2dz.data.repository.EverythingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(private val repository: EverythingRepository) :
    BaseViewModel() {
    fun fetchEverything() = repository.fetchEverything().cachedIn(viewModelScope)
}