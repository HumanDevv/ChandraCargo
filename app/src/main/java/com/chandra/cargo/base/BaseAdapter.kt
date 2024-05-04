package com.chandra.cargo.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseAdapter<T, VB : ViewBinding> :
    RecyclerView.Adapter<BaseViewHolder<T, VB>>() {

    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    abstract fun onBind(binding: VB, item: T)

    protected var items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent)
        return createViewHolder(binding)
    }

    abstract fun createViewHolder(binding: VB): BaseViewHolder<T, VB>

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        val item = items[position]
        onBind(holder.binding, item)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(list: List<T>?) {
        items.clear()
        items.addAll(list!!)
        notifyDataSetChanged()
    }
}