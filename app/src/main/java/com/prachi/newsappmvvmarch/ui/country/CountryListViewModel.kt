package com.prachi.newsappmvvmarch.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prachi.newsappmvvmarch.data.model.Countries
import com.prachi.newsappmvvmarch.data.repository.CountriesRepository
import com.prachi.newsappmvvmarch.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(private val countriesRepository: CountriesRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Countries>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Countries>>> = _uiState

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                val countries = countriesRepository.getCountries("countries.json")
                _uiState.value = UiState.Success(countries)
            }catch (e:Exception){
                _uiState.value = UiState.Error("Failed to Fetch Countries")
            }
        }
    }

}
