package com.prachi.newsappmvvmarch.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prachi.newsappmvvmarch.data.model.Languages
import com.prachi.newsappmvvmarch.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguagesRepository @Inject constructor(@ApplicationContext private val context: Context){

    fun getLanguage(fileName :String):List<Languages>{
        return parseJsonToLanguagesList(context.assets.open(fileName).bufferedReader().use {
            it.readText()
        })
    }

    private fun parseJsonToLanguagesList(jsonString: String): List<Languages> {
        return Gson().fromJson(jsonString, object : TypeToken<List<Languages>>() {}.type)
    }
}