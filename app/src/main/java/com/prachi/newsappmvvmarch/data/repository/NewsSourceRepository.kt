package com.prachi.newsappmvvmarch.data.repository

import com.prachi.newsappmvvmarch.data.api.NetworkService
import com.prachi.newsappmvvmarch.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourceRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

}