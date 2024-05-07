package com.chandra.cargo.ui.annoucement

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chandra.cargo.base.BaseAdapter
import com.chandra.cargo.base.BaseViewHolder
import com.chandra.cargo.databinding.ListAnnouncementBinding


class AnnouncementAdapter(
    val context: Context, ) :
    BaseAdapter<AnnouncementX,ListAnnouncementBinding>() {

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ListAnnouncementBinding {
        return ListAnnouncementBinding.inflate(inflater, parent, false)
    }

    override fun onBind(binding: ListAnnouncementBinding, item: AnnouncementX) {
        binding.tvAnnounceBy.text=item.name
        binding.tvDate.text=item.date
        binding.tvTitle.text=item.title
    }



    override fun createViewHolder(binding: ListAnnouncementBinding): BaseViewHolder<AnnouncementX, ListAnnouncementBinding> {
        return ExampleViewHolder(binding)
    }

    inner class ExampleViewHolder(override val binding: ListAnnouncementBinding) :
        BaseViewHolder<AnnouncementX, ListAnnouncementBinding>(binding) {

        override fun bind(item: AnnouncementX) {

        }
    }

   /* override fun getItemCount(): Int {
        return AnnouncementX!!.size
    }*/
}

