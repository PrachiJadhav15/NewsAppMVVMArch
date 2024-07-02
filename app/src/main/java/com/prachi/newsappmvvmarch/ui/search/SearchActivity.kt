package com.prachi.newsappmvvmarch.ui.search

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.prachi.newsappmvvmarch.NewsApplication
import com.prachi.newsappmvvmarch.databinding.ActivitySearchBinding
import com.prachi.newsappmvvmarch.di.component.DaggerActivityComponent
import com.prachi.newsappmvvmarch.di.module.ActivityModule
import com.prachi.newsappmvvmarch.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var adapter: SearchViewAdapter

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.searchRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        adapter.itemClickListener = {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(it))
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                searchViewModel.searchNewsByQuery(searchText)
                return true
            }
        })
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uiStateFlow.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.differ.submitList(it.data)
                            binding.searchRecyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@SearchActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.searchRecyclerView.visibility = View.GONE
                        }
                    }
                }

            }
        }

    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

  /*  companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }*/


}