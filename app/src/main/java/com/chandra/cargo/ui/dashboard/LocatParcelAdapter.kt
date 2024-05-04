package com.chandra.cargo.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.cargo.databinding.ListParcelStatusBinding
import com.chandra.cargo.ui.locate.LocateParcelFragment


class LocatParcelAdapter(
    val context: LocateParcelFragment,
    val parcelStatusList: List<String>) : RecyclerView.Adapter<LocatParcelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListParcelStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderSale = parcelStatusList[position]
        holder.bind(orderSale)


    }

    override fun getItemCount(): Int {
        return parcelStatusList.size
    }

    inner class ViewHolder(private val binding: ListParcelStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemParcel: String) {


            if (parcelStatusList.lastIndex==position){
                binding.view2.visibility= View.GONE
            }

        }
    }
}


