package com.chandra.cargo.ui.annoucement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.databinding.FragmentAnnouncementBinding
import com.chandra.cargo.ui.transaction.RecentTransaction
import com.chandra.cargo.ui.transaction.RecentTransactionAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnnouncementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var dataList2:ArrayList<Announcements>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnnouncementBinding {
        return FragmentAnnouncementBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text="Announcement"


        dataList2 = ArrayList()
        dataList2.add(Announcements("22,March"))
        dataList2.add(Announcements("21,March"))
        dataList2.add(Announcements("29,March"))

        binding.rvAnnouncement.layoutManager = LinearLayoutManager(mActivity,  LinearLayoutManager.VERTICAL, false)
        binding.rvAnnouncement.adapter = AnnouncementAdapter(mActivity, dataList2)
    }

}