package com.example.whitehelmettask.domain.usecase


import com.example.whitehelmettask.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchSuggestionsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(query: String): Flow<List<String>> {
        return repository.getSearchSuggestions(query)
    }
}
