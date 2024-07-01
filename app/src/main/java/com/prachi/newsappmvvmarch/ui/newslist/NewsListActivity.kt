package com.prachi.newsappmvvmarch.ui.newslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.prachi.newsappmvvmarch.NewsApplication
import com.prachi.newsappmvvmarch.data.model.NewsListArticle
import com.prachi.newsappmvvmarch.databinding.ActivityTopHeadlineBinding
import com.prachi.newsappmvvmarch.di.component.DaggerActivityComponent
import com.prachi.newsappmvvmarch.di.module.ActivityModule
import com.prachi.newsappmvvmarch.ui.base.UiState
import com.prachi.newsappmvvmarch.ui.country.COUNTRY
import com.prachi.newsappmvvmarch.ui.language.LANGUAGE
import com.prachi.newsappmvvmarch.ui.newssource.SOURCE
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListActivity : AppCompatActivity() {

    @Inject
    lateinit var newsListViewModel: NewsListViewModel

    @Inject
    lateinit var adapter: NewsListAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val sourceName = bundle?.getString(SOURCE)
        val countryName = bundle?.getString(COUNTRY)
        val languageSelection = bundle?.getString(LANGUAGE)
        sourceName?.let {
                newsListViewModel.fetchNewsListBySource(it)
        }
        countryName?.let {
                newsListViewModel.fetchNewsListByCountry(it)
        }
        languageSelection?.let {
            newsListViewModel.fetchNewsListByLanguage(it)
        }
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@NewsListActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<NewsListArticle>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}
