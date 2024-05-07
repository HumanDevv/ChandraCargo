package com.chandra.cargo.ui.locate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.databinding.FragmentLocateParcelBinding
import com.chandra.cargo.ui.dashboard.LocatParcelAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LocateParcelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LocateParcelFragment : BaseFragment<FragmentLocateParcelBinding>() {
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
    ): FragmentLocateParcelBinding {
        return FragmentLocateParcelBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text="Locate your parcel"
        binding.layoutHeader.ivImg.setImageDrawable(mActivity.getDrawable(R.drawable.tracking))

        setData()


    }

    fun setData() {
        val original = listOf("orange", "orange","orange",)

        val adapter = LocatParcelAdapter(this, original)
        binding.rvData.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvData.adapter = adapter
    }
}