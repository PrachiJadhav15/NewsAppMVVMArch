package com.prachi.newsappmvvmarch.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prachi.newsappmvvmarch.data.model.Languages
import com.prachi.newsappmvvmarch.databinding.NewsSourcesItemLayoutBinding

class LanguageSelectionAdapter(
    private val sourceList: ArrayList<Languages>
) : RecyclerView.Adapter<LanguageSelectionAdapter.DataViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    class DataViewHolder(
        private val binding: NewsSourcesItemLayoutBinding,
        private val onItemClick: ((String) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(source: Languages) {
            binding.textViewNewsTitle.text = source.languageName
            itemView.setOnClickListener {
                onItemClick?.invoke(source.languageCode)
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

    fun addData(list: List<Languages>) {
        sourceList.addAll(list)
    }
}
