package com.prachi.newsappmvvmarch.ui.country

import androidx.lifecycle.ViewModel

class CountryListViewModel: ViewModel() {

   // private val _uiState = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)

  //  val uiState: StateFlow<UiState<List<Source>>> = _uiState
    //val countryList : List<String> = listOf<String>("Argentina","Australia","Austria","Belarus","Belgium","Brazil","Canada","China","Columbia","Denmark","Egypt","France","Georgia","Germany","Hawaii","India","Iran","Italy","Japan","Kenya","Korea","Kuwait","Malaysia")
    val countryMap : Map<String,String> = mapOf<String,String>("ar" to "Argentina",
        "au" to "Australia", "at" to "Austria", "by" to "Belarus","be" to "Belgium",
        "br" to "Brazil","ca" to "Canada", "cn" to "China",
        "co" to "Columbia", "dk" to "Denmark","eg" to "Egypt", "fr" to "France",
        "ge" to "Georgia", "de" to  "Germany", "hk" to  "Hong Kong","in" to "India",
        "ir" to "Iran",  "it" to  "Italy",  "jp" to "Japan", "ke" to "Kenya",
        "kr" to "Korea", "kw" to "Kuwait", "my" to "Malaysia")

}
