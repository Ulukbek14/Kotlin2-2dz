package com.example.kotlin2_2dz.ui.fragment.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin2_2dz.base.BaseFetchRequest
import com.example.kotlin2_2dz.base.BaseViewModel
import com.example.kotlin2_2dz.domain.model.Sources
import com.example.kotlin2_2dz.domain.usecases.SourceUseCases
import com.example.kotlin2_2dz.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val useCases: SourceUseCases) :
    BaseViewModel(), BaseFetchRequest {

    private val _sourcesState = MutableLiveData<UIState<List<Sources>?>>()
    val sourcesState: LiveData<UIState<List<Sources>?>> = _sourcesState
    override var page: Int = 1

    init {
        fetchNews(1)
    }

    override fun fetchNews(page: Int) {
        subscribeTo(_sourcesState) {
            useCases.invoke(page)
        }
    }
}