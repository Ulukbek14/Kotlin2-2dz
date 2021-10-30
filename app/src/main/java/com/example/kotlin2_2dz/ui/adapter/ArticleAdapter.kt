package com.example.kotlin2_2dz.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kotlin2_2dz.base.BaseDiffUtilitemCallback
import com.example.kotlin2_2dz.databinding.ItemEverythingNewsBinding
import com.example.kotlin2_2dz.domain.model.EverythingModel
import com.example.kotlin2_2dz.extensions.dateFormatTime

class ArticleAdapter : ListAdapter<EverythingModel, ArticleAdapter.EverythingViewHolder>(BaseDiffUtilitemCallback<EverythingModel>()) {

    override fun onBindViewHolder(holder: EverythingViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EverythingViewHolder {
        return EverythingViewHolder(
            ItemEverythingNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class EverythingViewHolder(private val binding: ItemEverythingNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: EverythingModel) = with(binding) {
            Glide.with(ivShadowTop)
                .load(it.urlToImage)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean): Boolean {
                        pbProgressLoadPhoto.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean): Boolean {
                        pbProgressLoadPhoto.visibility = View.GONE
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivShadowTop)

            tvTitle.text = it.title
            tvDesc.text = it.description
            tvSource.text = it.source?.name
            tvTime.text = dateFormatTime(it.publishedAt)
            tvPublishedAt.text = dateFormatTime(it.publishedAt)
            tvAuthor.text = it.author
        }
    }
}