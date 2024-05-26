package com.prachi.newsappmvvmarch.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prachi.newsappmvvmarch.data.model.Article
import com.prachi.newsappmvvmarch.data.repository.NewsSourceRepository
import com.prachi.newsappmvvmarch.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class NewsSourcesViewModel(private val newsSource: NewsSourceRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {
        viewModelScope.launch {

        }
    }

}