package com.example.whitehelmettask.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.whitehelmettask.domain.model.Movie
import com.example.whitehelmettask.domain.usecase.GetMoviesUseCase
import com.example.whitehelmettask.domain.usecase.GetSearchSuggestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSearchSuggestionsUseCase: GetSearchSuggestionsUseCase
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val _searchSuggestions = MutableStateFlow<List<String>>(emptyList())
    val searchSuggestions: StateFlow<List<String>> = _searchSuggestions.asStateFlow()

    private val cachedTitles = mutableListOf<String>()

    val movies: Flow<PagingData<Movie>> = searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    MoviePagingSource(getMoviesUseCase, query)
                }
            ).flow
        }
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    getSearchSuggestionsUseCase(query)
                }
                .collect { suggestions ->
                    _searchSuggestions.value = suggestions
                    cachedTitles.clear()
                    cachedTitles.addAll(suggestions)
                }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery.value = newQuery

        // Provide cached suggestions immediately (fallback UI)
        _searchSuggestions.value = cachedTitles.filter {
            it.contains(newQuery, ignoreCase = true)
        }.take(10)
    }
}
