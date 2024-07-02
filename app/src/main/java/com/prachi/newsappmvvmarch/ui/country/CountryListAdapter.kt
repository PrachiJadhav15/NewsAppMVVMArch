package com.prachi.newsappmvvmarch.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prachi.newsappmvvmarch.data.model.Countries
import com.prachi.newsappmvvmarch.databinding.NewsSourcesItemLayoutBinding

class CountryListAdapter(
    private val sourceList: ArrayList<Countries>
) : RecyclerView.Adapter<CountryListAdapter.DataViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    class DataViewHolder(
        private val binding: NewsSourcesItemLayoutBinding,
        private val onItemClick: ((String) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Countries) {
            binding.textViewNewsTitle.text = country.countryName
            itemView.setOnClickListener {
                onItemClick?.invoke(country.countryCode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            NewsSourcesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    override fun getItemCount(): Int = sourceList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(sourceList[position])

    fun addData(list: List<Countries>) {
        sourceList.addAll(list)
    }
}
