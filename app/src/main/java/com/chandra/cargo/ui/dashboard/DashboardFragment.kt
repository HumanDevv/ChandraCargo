package com.chandra.cargo.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.databinding.FragmentDashboardBinding
import com.chandra.cargo.ui.annoucement.AnnouncementFragment
import com.chandra.cargo.ui.helpline.HelpLineFragment
import com.chandra.cargo.ui.locate.LocateParcelFragment
import com.chandra.cargo.ui.network.OurNetworkFragment
import com.chandra.cargo.ui.transaction.RecentTransactionFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(layoutInflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutHeader.ivBack.visibility = View.GONE
        binding.layoutHeader.ivImg.visibility = View.GONE
        binding.layoutHeader.tvHeading.text = "Dashboard"


        binding.cvRecentTransaction.setOnClickListener {
            mActivity.replaceFragment(true, RecentTransactionFragment())
        }

        binding.cvLocate.setOnClickListener {
            mActivity.replaceFragment(true, LocateParcelFragment())
        }
        binding.cvNetwork.setOnClickListener {
            mActivity.replaceFragment(true, OurNetworkFragment())
        }

        binding.cvAnnouncement.setOnClickListener {
            mActivity.replaceFragment(true, AnnouncementFragment())
        }
        binding.cvHelpline.setOnClickListener {
            mActivity.replaceFragment(true, HelpLineFragment())
        }
    }
}