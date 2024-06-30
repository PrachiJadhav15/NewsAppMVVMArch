package com.prachi.newsappmvvmarch.ui.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prachi.newsappmvvmarch.data.model.NewsListArticle
import com.prachi.newsappmvvmarch.data.repository.NewsListRepository
import com.prachi.newsappmvvmarch.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsListViewModel(private val newsListByRepository: NewsListRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<NewsListArticle>>>(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<NewsListArticle>>> = _uiState

     fun fetchNewsListBySource(source: String) {
        viewModelScope.launch {
            newsListByRepository.getNewsListBySource(source).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun fetchNewsListByCountry(country: String) {
        viewModelScope.launch {
            newsListByRepository.getNewsListByCountry(country).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}
