package com.prachi.newsappmvvmarch.data.model

data class ArticleBySource(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: NewsListSource,
    val title: String,
    val url: String,
    val urlToImage: String
)