package com.prachi.newsappmvvmarch.ui.language

import androidx.lifecycle.ViewModel

class LanguageViewModel: ViewModel() {

    val languageMap : Map<String,String> = mapOf<String,String>("en" to "English",
        "hi" to "Hindi", "ar" to "Arabic", "bn" to "Bengali","zh" to "Chinese",
        "fr" to "French","de" to "German", "el" to "Greek",
        "co" to "Columbia", "dk" to "Denmark","eg" to "Egypt", "fr" to "France",
        "ge" to "Georgia", "de" to  "Germany", "hk" to  "Hong Kong","in" to "India",
        "ir" to "Iran",  "it" to  "Italy",  "jp" to "Japan", "ke" to "Kenya",
        "kr" to "Korea", "kw" to "Kuwait", "my" to "Malaysia")
}
