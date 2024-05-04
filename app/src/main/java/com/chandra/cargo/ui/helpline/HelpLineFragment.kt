package com.chandra.cargo.ui.helpline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.databinding.FragmentHelpLineBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HelpLineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HelpLineFragment : BaseFragment<FragmentHelpLineBinding>() {
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
    ): FragmentHelpLineBinding {
        return FragmentHelpLineBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text="Helpline and Grievance"


        binding.cvHelpline.setOnClickListener {
            binding.layoutHelpLine.visibility=View.VISIBLE
            binding.layoutGrievanceForm.visibility=View.GONE
            binding.cvHelpline.strokeColor=mActivity.resources.getColor(R.color.purple,null)
            binding.cvHelpline.strokeWidth=7
            binding.cvGrievance.strokeColor=mActivity.resources.getColor(R.color.purple,null)
            binding.cvGrievance.strokeWidth=3
        }
        binding.cvGrievance.setOnClickListener {
            binding.layoutHelpLine.visibility=View.GONE
            binding.layoutGrievanceForm.visibility=View.VISIBLE
            binding.cvGrievance.strokeColor=mActivity.resources.getColor(R.color.purple,null)
            binding.cvGrievance.strokeWidth=7
            binding.cvHelpline.strokeColor=mActivity.resources.getColor(R.color.purple,null)
            binding.cvHelpline.strokeWidth=3
        }
    }
}