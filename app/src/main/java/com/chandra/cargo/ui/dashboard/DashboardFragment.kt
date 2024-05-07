package com.chandra.cargo.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.common.DialogUtils
import com.chandra.cargo.databinding.FragmentDashboardBinding
import com.chandra.cargo.interfaces.DialogClickInterface
import com.chandra.cargo.ui.annoucement.AnnouncementFragment
import com.chandra.cargo.ui.auth.LoginActivity
import com.chandra.cargo.ui.helpline.HelpLineFragment
import com.chandra.cargo.ui.locate.LocateParcelFragment
import com.chandra.cargo.ui.network.OurNetworkFragment
import com.chandra.cargo.ui.transaction.RecentTransactionFragment
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import com.rudraansh.rudraapay.utils.sharedPrefrence.Constant

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
    lateinit var userName: String
    private lateinit var appPreferences : AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(layoutInflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutHeader.ivBack.visibility=View.GONE
        binding.layoutHeader.ivImg.visibility=View.GONE
        binding.layoutHeader.ivLogout.visibility=View.VISIBLE
        appPreferences=  AppPreferences.getInstance(mActivity)
        userName=appPreferences.getString(Constant.userName,"")
        binding.layoutHeader.tvHeading.text="Welcome, "+userName

        binding.cvRecentTransaction.setOnClickListener {
            mActivity.replaceFragment(true,RecentTransactionFragment())
        }

        binding.cvLocate.setOnClickListener {
            mActivity.replaceFragment(true,LocateParcelFragment())
        }
        binding.cvNetwork.setOnClickListener {
            mActivity.replaceFragment(true,OurNetworkFragment())
        }

        binding.layoutHeader.ivLogout.setOnClickListener {
            DialogUtils.logoutDialog(mActivity,object : DialogClickInterface{
                override fun sureClickEvent() {
                    appPreferences.saveString(Constant.KEY_ISLOGIN,"false")
                   val  intent=Intent(mActivity,LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    mActivity.finish()
                }
            })
        }
        binding.cvAnnouncement.setOnClickListener {
            mActivity.replaceFragment(true,AnnouncementFragment())
        }
        binding.cvHelpline.setOnClickListener {
            mActivity.replaceFragment(true,HelpLineFragment())
        }
    }
}