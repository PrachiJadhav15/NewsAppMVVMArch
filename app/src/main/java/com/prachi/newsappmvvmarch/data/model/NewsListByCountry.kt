package com.prachi.newsappmvvmarch.data.model

data class NewsListByCountry(
    val articles: List<NewsListArticle>,
    val status: String,
    val totalResults: Int
)