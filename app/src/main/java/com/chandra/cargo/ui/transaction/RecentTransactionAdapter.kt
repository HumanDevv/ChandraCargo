package com.chandra.cargo.ui.transaction

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.cargo.base.BaseAdapter
import com.chandra.cargo.base.BaseViewHolder
import com.chandra.cargo.databinding.ListAnnouncementBinding
import com.chandra.cargo.databinding.ListTransactionBinding
import com.chandra.cargo.ui.annoucement.AnnouncementX
import com.chandra.cargo.ui.transaction.model.RecentTransactionX


class RecentTransactionAdapter(
    val context: Context, ) :
    BaseAdapter<RecentTransactionX, ListTransactionBinding>() {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ListTransactionBinding {
        return ListTransactionBinding.inflate(inflater, parent, false)
    }

    override fun onBind(binding: ListTransactionBinding, item: RecentTransactionX) {
        binding.tvFrom.text=item.form
        binding.tvTo.text=item.to
        binding.tvDate.text=item.date
        binding.tvAmount.text="₹"+item.payment
        binding.tvPayment.text=item.paymentMode
        binding.tvMarkNo.text=item.markNo
    }



    override fun createViewHolder(binding: ListTransactionBinding): BaseViewHolder<RecentTransactionX, ListTransactionBinding> {
        return ExampleViewHolder(binding)
    }

    inner class ExampleViewHolder(override val binding: ListTransactionBinding) :
        BaseViewHolder<RecentTransactionX, ListTransactionBinding>(binding) {

        override fun bind(item: RecentTransactionX) {

        }
    }

    /* override fun getItemCount(): Int {
         return AnnouncementX!!.size
     }*/
}

