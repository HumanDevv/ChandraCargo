package com.chandra.cargo.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseViewHolder<T, VB : ViewBinding>(
    open val binding: VB
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}
