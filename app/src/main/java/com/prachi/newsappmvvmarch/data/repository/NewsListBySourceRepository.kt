package com.prachi.newsappmvvmarch.data.repository

import com.prachi.newsappmvvmarch.data.api.NetworkService
import com.prachi.newsappmvvmarch.data.model.ArticleBySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListBySourceRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsListBySource(source: String): Flow<List<ArticleBySource>> {
        return flow {
            emit(networkService.getNewsListBySource(source))
        }.map {
            it.articles
        }
    }
}
