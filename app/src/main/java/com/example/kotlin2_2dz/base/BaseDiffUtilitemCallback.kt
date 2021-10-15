package com.example.kotlin2_2dz.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilitemCallback<T : IBaseDiffModel> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(
        oldItem: T,
        newItem: T): Boolean {
        return oldItem.url == newItem.url
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: T,
        newItem: T): Boolean {
        return oldItem == newItem
    }

}