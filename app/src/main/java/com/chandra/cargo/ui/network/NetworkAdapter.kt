package com.chandra.cargo.ui.network

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.cargo.base.BaseAdapter
import com.chandra.cargo.base.BaseViewHolder
import com.chandra.cargo.databinding.ListAnnouncementBinding
import com.chandra.cargo.databinding.ListNetworkBinding
import com.chandra.cargo.ui.network.model.Citylist


class NetworkAdapter(
    val context: Context,
) :
    BaseAdapter<Citylist, ListNetworkBinding>() {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ListNetworkBinding {
        return ListNetworkBinding.inflate(inflater, parent, false)
    }

    override fun onBind(binding: ListNetworkBinding, item: Citylist) {
        binding.tvName.text = item.City
        binding.tvNo.text = item.counter.toString()
    }


    override fun createViewHolder(binding: ListNetworkBinding): BaseViewHolder<Citylist, ListNetworkBinding> {
        return ExampleViewHolder(binding)
    }

    inner class ExampleViewHolder(override val binding: ListNetworkBinding) :
        BaseViewHolder<Citylist, ListNetworkBinding>(binding) {

        override fun bind(item: Citylist) {
            if (adapterPosition == itemCount - 1) {
                // Last item, hide view2
                binding.view2.visibility = View.GONE
            } else {
                binding.view2.visibility = View.VISIBLE
            }
        }
    }

    /* override fun getItemCount(): Int {
         return AnnouncementX!!.size
     }*/
}

