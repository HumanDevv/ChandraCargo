package com.chandra.cargo.ui.transaction

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.cargo.databinding.ListTransactionBinding


class RecentTransactionAdapter(
    val context: Context,
    private val dataList: ArrayList<RecentTransaction>
) :
    RecyclerView.Adapter<RecentTransactionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.tvDate.text=dataList[position].date


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

   inner class ViewHolder( val binding: ListTransactionBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}

