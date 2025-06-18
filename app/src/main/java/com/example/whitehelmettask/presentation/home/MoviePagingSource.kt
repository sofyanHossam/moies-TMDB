package com.example.whitehelmettask.presentation.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.whitehelmettask.domain.model.Movie
import com.example.whitehelmettask.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.first

class MoviePagingSource(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val movies = getMoviesUseCase(query, page).first()

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}
