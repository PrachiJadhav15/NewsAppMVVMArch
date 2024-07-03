package com.prachi.newsappmvvmarch.ui.newssource

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.prachi.newsappmvvmarch.NewsApplication
import com.prachi.newsappmvvmarch.data.model.Source
import com.prachi.newsappmvvmarch.databinding.NewsSourcesActivityBinding
import com.prachi.newsappmvvmarch.di.component.DaggerActivityComponent
import com.prachi.newsappmvvmarch.di.module.ActivityModule
import com.prachi.newsappmvvmarch.ui.base.UiState
import com.prachi.newsappmvvmarch.ui.error.ErrorActivity
import com.prachi.newsappmvvmarch.ui.newslist.NewsListActivity
import com.prachi.newsappmvvmarch.utils.AppConstant.SOURCE
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsSourcesActivity : AppCompatActivity() {

    @Inject
    lateinit var newsListViewModel: NewsSourcesViewModel

    @Inject
    lateinit var adapter: NewsSourcesAdapter

    private lateinit var binding: NewsSourcesActivityBinding

    private var resultLauncher =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            newsListViewModel.fetchNewsSources()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = NewsSourcesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.newsSourcesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        adapter.onItemClick = {
            adapterOnClick(it)
        }
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
                            binding.newsSourcesRecyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.newsSourcesRecyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            startErrorScreen()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(newsSourceList: List<Source>) {
        adapter.addData(newsSourceList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    /* Opens NewsListActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(newsSource: Source) {
        val intent = Intent(this, NewsListActivity()::class.java)
        intent.putExtra(SOURCE, newsSource.id)
        startActivity(intent)
    }

    private fun startErrorScreen() {
        val intent = Intent(this@NewsSourcesActivity, ErrorActivity::class.java)
        resultLauncher.launch(intent)
    }
}
