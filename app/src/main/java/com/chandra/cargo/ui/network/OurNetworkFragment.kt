package com.chandra.cargo.ui.network

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.databinding.FragmentOurNetworkBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OurNetworkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OurNetworkFragment : BaseFragment<FragmentOurNetworkBinding>() {
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
    ): FragmentOurNetworkBinding {
        return FragmentOurNetworkBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text="Our Networks"

        binding.tvStateWise.setOnClickListener {
            binding.tvStateWise.setTextColor(resources.getColor(R.color.white))
            binding.tvStateWise.backgroundTintList=resources.getColorStateList(R.color.purple,null)
            binding.tvCityWise.setTextColor(resources.getColor(R.color.black))
            binding.tvCityWise.backgroundTintList=resources.getColorStateList(R.color.light_purple,null)
        }
        binding.tvCityWise.setOnClickListener {
            binding.tvCityWise.setTextColor(resources.getColor(R.color.white))
            binding.tvCityWise.backgroundTintList=resources.getColorStateList(R.color.purple,null)
            binding.tvStateWise.setTextColor(resources.getColor(R.color.black))
            binding.tvStateWise.backgroundTintList=resources.getColorStateList(R.color.light_purple,null)
        }
    }


}