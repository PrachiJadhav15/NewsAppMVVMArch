package com.prachi.newsappmvvmarch.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prachi.newsappmvvmarch.data.model.Countries
import com.prachi.newsappmvvmarch.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(@ApplicationContext private val context: Context ){

    fun getCountries(fileName:String):List<Countries>{
        return parseJsonToCountryList(context.assets.open(fileName).bufferedReader().use {
            it.readText()
        })
    }

    private fun parseJsonToCountryList(jsonString: String): List<Countries> {
        return Gson().fromJson(jsonString, object : TypeToken<List<Countries>>() {}.type)
    }
}