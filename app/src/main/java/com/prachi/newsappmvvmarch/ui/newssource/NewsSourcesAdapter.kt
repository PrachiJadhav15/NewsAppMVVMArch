package com.prachi.newsappmvvmarch.ui.newssource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prachi.newsappmvvmarch.data.model.Source
import com.prachi.newsappmvvmarch.databinding.NewsSourcesItemLayoutBinding

class NewsSourcesAdapter(
    private val sourceList: ArrayList<Source>
) : RecyclerView.Adapter<NewsSourcesAdapter.DataViewHolder>() {

    var onItemClick: ((Source) -> Unit)? = null

    class DataViewHolder(
        private val binding: NewsSourcesItemLayoutBinding,
        private val onItemClick: ((Source) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(source: Source) {
            binding.textViewNewsTitle.text = source.name
            itemView.setOnClickListener {
                onItemClick?.invoke(source)
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

    fun addData(list: List<Source>) {
        sourceList.addAll(list)
    }
}
