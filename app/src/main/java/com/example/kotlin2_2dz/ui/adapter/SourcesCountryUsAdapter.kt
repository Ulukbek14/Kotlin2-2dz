package com.example.kotlin2_2dz.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2_2dz.base.BaseDiffUtilitemCallback
import com.example.kotlin2_2dz.databinding.ItemSourcesCountryUsBinding
import com.example.kotlin2_2dz.model.Sources

class SourcesCountryUsAdapter : PagingDataAdapter<Sources, SourcesCountryUsAdapter.SourcesCountryUsViewHolder>(BaseDiffUtilitemCallback<Sources>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesCountryUsViewHolder {
        return SourcesCountryUsViewHolder(
            ItemSourcesCountryUsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SourcesCountryUsViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class SourcesCountryUsViewHolder(private val binding: ItemSourcesCountryUsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: Sources) = with(binding) {
            tvName.text = it.name
            tvCountry.text = it.country
            tvDesc.text = it.description
            tvCategory.text = it.category
            tvLanguageCountry.text = it.language
        }

    }
}