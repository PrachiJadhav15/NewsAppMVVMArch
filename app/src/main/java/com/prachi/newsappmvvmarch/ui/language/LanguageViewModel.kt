package com.prachi.newsappmvvmarch.ui.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prachi.newsappmvvmarch.data.model.Languages
import com.prachi.newsappmvvmarch.data.repository.LanguagesRepository
import com.prachi.newsappmvvmarch.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageViewModel @Inject constructor(private val languagesRepository: LanguagesRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Languages>>>(UiState.Loading)

    val uiState : StateFlow<UiState<List<Languages>>> = _uiState

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        viewModelScope.launch {
            try {
             val languages = languagesRepository.getLanguage("languages.json")
                _uiState.value = UiState.Success(languages)
            }catch(e:Exception){
                _uiState.value = UiState.Error("Failed to Fetch Languages")
            }
        }
    }
}
