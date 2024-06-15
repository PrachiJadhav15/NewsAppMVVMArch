package com.prachi.newsappmvvmarch.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prachi.newsappmvvmarch.data.model.ArticleBySource
import com.prachi.newsappmvvmarch.data.repository.NewsListBySourceRepository
import com.prachi.newsappmvvmarch.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsListViewModel(private val newsListBySourceRepository: NewsListBySourceRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ArticleBySource>>>(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<ArticleBySource>>> = _uiState

    init {
        fetchNewsListBySource("abc-news")
    }
    // TODO("pass this query parameter 'source' thr viewModel provider")
    private fun fetchNewsListBySource(source: String) {
        viewModelScope.launch {
            newsListBySourceRepository.getNewsListBySource(source).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}
