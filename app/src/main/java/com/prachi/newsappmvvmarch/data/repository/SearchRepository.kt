package com.prachi.newsappmvvmarch.data.repository

import com.prachi.newsappmvvmarch.data.api.NetworkService
import com.prachi.newsappmvvmarch.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsBySearchQuery(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsBySearchQuery(query))
        }.map { it.articles }
    }
}