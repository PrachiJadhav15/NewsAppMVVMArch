package com.prachi.newsappmvvmarch.ui.country

import android.content.Intent
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
import com.prachi.newsappmvvmarch.data.model.Countries
import com.prachi.newsappmvvmarch.databinding.NewsSourcesActivityBinding
import com.prachi.newsappmvvmarch.di.component.DaggerActivityComponent
import com.prachi.newsappmvvmarch.di.module.ActivityModule
import com.prachi.newsappmvvmarch.ui.base.UiState
import com.prachi.newsappmvvmarch.ui.newslist.NewsListActivity
import com.prachi.newsappmvvmarch.utils.AppConstant.COUNTRY_CODE
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListActivity : AppCompatActivity() {

    @Inject
    lateinit var countryListViewModel: CountryListViewModel

    @Inject
    lateinit var adapter: CountryListAdapter

    private lateinit var binding: NewsSourcesActivityBinding

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
                countryListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.newsSourcesRecyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@CountryListActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.newsSourcesRecyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun renderList(newsSourceList: List<Countries>) {
        adapter.addData(newsSourceList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    /* Opens NewsListActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(countryCode: String) {
        val intent = Intent(this, NewsListActivity()::class.java)
        intent.putExtra(COUNTRY_CODE, countryCode)
        startActivity(intent)
    }
}
