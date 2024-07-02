package com.prachi.newsappmvvmarch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prachi.newsappmvvmarch.data.model.Article
import com.prachi.newsappmvvmarch.data.repository.SearchRepository
import com.prachi.newsappmvvmarch.ui.base.UiState
import com.prachi.newsappmvvmarch.utils.AppConstant.DEBOUNCE_TIMEOUT
import com.prachi.newsappmvvmarch.utils.AppConstant.MIN_SEARCH_CHAR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _uiStateFlow =
        MutableStateFlow<UiState<List<Article>>>(UiState.Success(emptyList()))
    val uiStateFlow: StateFlow<UiState<List<Article>>> = _uiStateFlow

    private val query = MutableStateFlow("")

    init {
        newsFlowBySearchQuery()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun newsFlowBySearchQuery() {
        viewModelScope.launch {
            query.debounce(DEBOUNCE_TIMEOUT).filter {
                if(it.isNotEmpty() && it.length>= MIN_SEARCH_CHAR){
                    return@filter true
                }else{
                    _uiStateFlow.value = UiState.Success(emptyList())
                    return@filter false
                }
            }.distinctUntilChanged()
                .flatMapLatest {
                    _uiStateFlow.value = UiState.Loading
                    return@flatMapLatest searchRepository.getNewsBySearchQuery(it)
                        .catch {
                            e-> _uiStateFlow.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect{
                    _uiStateFlow.value = UiState.Success(it)
                }
        }
    }

    fun searchNewsByQuery(searchQuery: String) {
        query.value = searchQuery
    }
}