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
import com.chandra.cargo.databinding.ListViewnetworkBinding
import com.chandra.cargo.ui.network.model.Citylist
import com.chandra.cargo.ui.network.model.Counter


class NetworkDetailAdapter(
    val context: Context, ) :
    BaseAdapter<Counter,ListViewnetworkBinding>() {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ListViewnetworkBinding {
        return ListViewnetworkBinding.inflate(inflater, parent, false)
    }

    override fun onBind(binding: ListViewnetworkBinding, item: Counter) {
    binding.tvCountername.text=item.counterName
    binding.tvAddress.text=item.counterLocation
    }
    override fun createViewHolder(binding: ListViewnetworkBinding): BaseViewHolder<Counter, ListViewnetworkBinding> {
        return ExampleViewHolder(binding)
    }

    inner class ExampleViewHolder(override val binding: ListViewnetworkBinding) :
        BaseViewHolder<Counter, ListViewnetworkBinding>(binding) {

        override fun bind(item: Counter) {
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

