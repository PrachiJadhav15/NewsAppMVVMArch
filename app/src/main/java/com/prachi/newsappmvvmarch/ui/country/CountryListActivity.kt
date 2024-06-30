package com.prachi.newsappmvvmarch.ui.country

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.prachi.newsappmvvmarch.NewsApplication
import com.prachi.newsappmvvmarch.databinding.NewsSourcesActivityBinding
import com.prachi.newsappmvvmarch.di.component.DaggerActivityComponent
import com.prachi.newsappmvvmarch.di.module.ActivityModule
import com.prachi.newsappmvvmarch.ui.newslist.NewsListActivity
import javax.inject.Inject

const val COUNTRY = "country"

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
        binding.progressBar.visibility = View.GONE
        renderList(countryListViewModel.countryMap.values.toList())
    }


    private fun renderList(newsSourceList: List<String>) {
        adapter.addData(newsSourceList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    /* Opens NewsListActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(country: String) {
        val countryKey = countryListViewModel.countryMap.filterValues { it == country }.keys.first()
        val intent = Intent(this, NewsListActivity()::class.java)
        intent.putExtra(COUNTRY, countryKey)
        startActivity(intent)
    }
}
