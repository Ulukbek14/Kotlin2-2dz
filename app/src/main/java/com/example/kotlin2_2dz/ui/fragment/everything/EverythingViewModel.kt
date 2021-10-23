package com.example.kotlin2_2dz.ui.fragment.everything

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin2_2dz.base.BaseFetchRequest
import com.example.kotlin2_2dz.base.BaseViewModel
import com.example.kotlin2_2dz.domain.model.EverythingModel
import com.example.kotlin2_2dz.domain.usecases.EverythingUseCases
import com.example.kotlin2_2dz.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(private val useCases: EverythingUseCases) : BaseViewModel(), BaseFetchRequest {

    private val _everythingState = MutableLiveData<UIState<List<EverythingModel>?>>()
    val everythingState: LiveData<UIState<List<EverythingModel>?>> = _everythingState
    override var page: Int = 1

    init {
        fetchNews(1)
    }

    override fun fetchNews(page: Int) {
        subscribeTo(_everythingState) {
            useCases.invoke(page)
        }
    }
}
