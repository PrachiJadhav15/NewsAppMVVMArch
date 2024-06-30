package com.prachi.newsappmvvmarch.data.model

data class NewsListBySource(
    val articles: List<NewsListArticle>,
    val status: String,
    val totalResults: Int
)