package com.prachi.newsappmvvmarch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prachi.newsappmvvmarch.data.model.Article
import com.prachi.newsappmvvmarch.databinding.TopHeadlineItemLayoutBinding
import com.prachi.newsappmvvmarch.utils.ItemClickListener

class SearchViewAdapter: RecyclerView.Adapter<SearchViewAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, itemClickListener: ItemClickListener<String>) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.imageUrl)
                .into(binding.imageViewBanner)

            itemView.setOnClickListener {
                itemClickListener(article.url)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    // tool that will take the two list and tell the differences
    val differ = AsyncListDiffer(this, differCallback)
    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(differ.currentList[position], itemClickListener)



}