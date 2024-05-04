package com.chandra.cargo.ui.network

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.databinding.FragmentOurNetworkBinding
import com.chandra.cargo.ui.network.model.Compaign

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
    var campaignList: MutableList<Compaign>? = null

    var stateId: String = ""
    var stateName: String = ""



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        campaignList?.add(Compaign("1","Rajasthan"))
        campaignList?.add(Compaign("2","Punjab"))
        campaignList?.add(Compaign("3","Haryana"))
        campaignList?.add(Compaign("4","Delhi"))
        setCanpaignList(campaignList!!)

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text="Our Networks"

        binding.tvStateWise.setOnClickListener {

            binding.cvSelect.hint="Select State"
            binding.tvStateWise.setTextColor(resources.getColor(R.color.white))
            binding.tvStateWise.backgroundTintList=resources.getColorStateList(R.color.purple,null)
            binding.tvCityWise.setTextColor(resources.getColor(R.color.black))
            binding.tvCityWise.backgroundTintList=resources.getColorStateList(R.color.light_purple,null)
        }

        binding.tvCityWise.setOnClickListener {
            binding.cvSelect.hint="Select City"
            binding.tvCityWise.setTextColor(resources.getColor(R.color.white))
            binding.tvCityWise.backgroundTintList=resources.getColorStateList(R.color.purple,null)
            binding.tvStateWise.setTextColor(resources.getColor(R.color.black))
            binding.tvStateWise.backgroundTintList=resources.getColorStateList(R.color.light_purple,null)
        }







        binding.autocompleteTitle.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            //   val selectedItem = parent.getItemAtPosition(position) as String
            val selectedState = campaignList?.get(position)
            // Handle the selected Campaign object
            Log.d("Spinner", "Selected item: $selectedState")
            // Access the properties of selectedCampaign as needed

            stateId = selectedState!!.stateId
            stateName = selectedState.stateName

        }






    }

    private fun setCanpaignList(list: MutableList<Compaign>) {
        // Modify the list
        // list.add(0, Compaign("0", "Select Campaign Name", "", "Product Name", "", "", "", ""))

        // Generate shopNames
        val stateName = list.map { it.stateName }

        // Create ArrayAdapter with shopNames
        val adapter = ArrayAdapter(requireContext(), R.layout.state_spinner_item, stateName)
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set adapter to the spinner
        binding.autocompleteTitle.setAdapter(adapter)
    }
}