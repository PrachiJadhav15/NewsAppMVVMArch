package com.prachi.newsappmvvmarch.data.model

data class NewsListBySource(
    val articles: List<ArticleBySource>,
    val status: String,
    val totalResults: Int
)