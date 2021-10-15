package com.example.kotlin2_2dz.data.repository.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin2_2dz.constants.Constants
import com.example.kotlin2_2dz.data.network.apiservices.SourceApiService
import com.example.kotlin2_2dz.model.Sources
import retrofit2.HttpException
import java.io.IOException

class SourceCountryUS(private val service: SourceApiService, ) : PagingSource<Int, Sources>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Sources> {
        val position = params.key ?: Constants.NEWS_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchSourcesCountryUs("us", position)
            val next = position + 1
            LoadResult.Page(
                data = response.sources,
                prevKey = null,
                nextKey = next)

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Sources>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?:
            anchorPage?.nextKey?.minus(1)
        }
    }
}