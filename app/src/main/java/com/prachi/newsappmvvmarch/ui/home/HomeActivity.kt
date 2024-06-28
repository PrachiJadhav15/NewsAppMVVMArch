package com.prachi.newsappmvvmarch.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.prachi.newsappmvvmarch.R
import com.prachi.newsappmvvmarch.ui.country.CountryListActivity
import com.prachi.newsappmvvmarch.ui.newssource.NewsSourcesActivity
import com.prachi.newsappmvvmarch.ui.topheadline.TopHeadlineActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun startTopHeadlineActivity(view: View) {
        startActivity(Intent(this@HomeActivity, TopHeadlineActivity::class.java))
    }

    fun startNewsSourceActivity(view: View) {
        startActivity(Intent(this@HomeActivity, NewsSourcesActivity::class.java))
    }

    fun startCountiesActivity(view: View) {
        startActivity(Intent(this@HomeActivity, CountryListActivity::class.java))
    }

    fun startLanguageActivity(view: View) {

    }

    fun startSearchActivity(view: View) {

    }
}
