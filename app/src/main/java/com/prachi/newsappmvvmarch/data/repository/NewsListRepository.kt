package com.prachi.newsappmvvmarch.data.repository

import com.prachi.newsappmvvmarch.data.api.NetworkService
import com.prachi.newsappmvvmarch.data.model.NewsListArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsListBySource(source: String): Flow<List<NewsListArticle>> {
        return flow {
            emit(networkService.getNewsListBySource(source))
        }.map {
            it.articles
        }
    }

    fun getNewsListByCountry(country: String): Flow<List<NewsListArticle>> {
        return flow {
            emit(networkService.getNewsListByCountry(country))
        }.map {
            it.articles
        }
    }

    fun getNewsListByLanguage(language: String): Flow<List<NewsListArticle>> {
        return flow {
            emit(networkService.getNewsListByLanguage(language))
        }.map {
            it.articles
        }
    }
}
