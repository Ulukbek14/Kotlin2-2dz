package com.example.kotlin2_2dz.ui.fragment.topheadlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin2_2dz.base.BaseFetchRequest
import com.example.kotlin2_2dz.base.BaseViewModel
import com.example.kotlin2_2dz.domain.model.EverythingModel
import com.example.kotlin2_2dz.domain.usecases.TopHeadLinesUseCases
import com.example.kotlin2_2dz.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(private val useCases: TopHeadLinesUseCases) :
    BaseViewModel(), BaseFetchRequest {

    private val _topHeadlinesState = MutableLiveData<UIState<List<EverythingModel>?>>()
    val topHeadlinesState: LiveData<UIState<List<EverythingModel>?>> = _topHeadlinesState
    override var page: Int = 1

    init {
        fetchNews(1)
    }

    override fun fetchNews(page: Int) {
        subscribeTo(_topHeadlinesState) {
            useCases.invoke(page)
        }
    }

}