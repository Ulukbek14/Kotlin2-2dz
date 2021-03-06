package com.example.kotlin2_2dz.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin2_2dz.common.resource.Resource
import com.example.kotlin2_2dz.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> subscribeTo(
        state: MutableLiveData<UIState<T>>,
        request: () -> Flow<Resource<T>>) {

        viewModelScope.launch {
            request().collect {
                when (it) {
                    is Resource.Loading -> {
                        state.value = UIState.Loading()
                    }
                    is Resource.Error -> {
                        it.message?.let { error ->
                            state.value = UIState.Error(error)
                        }
                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            state.value = UIState.Success(data)
                        }
                    }
                }
            }
        }
    }
}