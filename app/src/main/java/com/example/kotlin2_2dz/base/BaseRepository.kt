package com.example.kotlin2_2dz.base

import com.example.kotlin2_2dz.common.resource.Resource
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    data = null, message = e.localizedMessage ?: "Error Occurred"
                )
            )
        }
    }
}