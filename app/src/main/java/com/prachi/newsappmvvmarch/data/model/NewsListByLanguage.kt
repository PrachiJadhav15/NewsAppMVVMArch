package com.prachi.newsappmvvmarch.data.model

data class NewsListByLanguage(
    val articles: List<NewsListArticle>,
    val status: String,
    val totalResults: Int
)