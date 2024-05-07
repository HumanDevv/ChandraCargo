package com.chandra.cargo.ui.network

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.common.AppState
import com.chandra.cargo.common.DialogUtils
import com.chandra.cargo.databinding.FragmentOurNetworkBinding
import com.chandra.cargo.ui.annoucement.AnnouncementAdapter
import com.chandra.cargo.ui.helpline.HelpLineViewModel
import com.chandra.cargo.ui.network.model.CityX
import com.chandra.cargo.ui.network.model.Compaign
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import com.rudraansh.rudraapay.utils.sharedPrefrence.Constant
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OurNetworkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class OurNetworkFragment : BaseFragment<FragmentOurNetworkBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var appPreferences: AppPreferences
    private lateinit var progress: Dialog
    private lateinit var userID: String
    var cityList: MutableList<CityX>? = null

    private val viewModel: NetworkViewModel by lazy {
        ViewModelProvider(this)[NetworkViewModel::class.java]
    }


    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOurNetworkBinding {
        return FragmentOurNetworkBinding.inflate(layoutInflater)
    }

    var stateId: String = ""
    var stateName: String = ""


    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = DialogUtils.showProgress(mActivity)
        appPreferences = AppPreferences.getInstance(requireActivity())
        userID = appPreferences.getString(Constant.userId, "")

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text = "Our Networks"
        binding.layoutHeader.ivImg.setImageDrawable(mActivity.getDrawable(R.drawable.network))

        /*   binding.tvStateWise.setOnClickListener {

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
           }*/

        binding.autocompleteTitle.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                //   val selectedItem = parent.getItemAtPosition(position) as String
                val selectedState = cityList?.get(position)
                // Handle the selected Campaign object
                Log.d("Spinner", "Selected item: $selectedState")
                // Access the properties of selectedCampaign as needed

                stateId = selectedState!!.CityId
                stateName = selectedState.City
                viewModel.NetworkAPI(stateId)

                Log.d("SelectedIndex", "$stateId  -  $stateName")

                if (stateId=="000") {
                    binding.ivSearch.visibility=View.VISIBLE
                    binding.autocompleteTitle.showDropDown()
                    binding.autocompleteTitle.text.clear()
                    binding.autocompleteTitle.setEnabled(true)
                    binding.autocompleteTitle.isCursorVisible=true
                    binding.autocompleteTitle.setInputType(InputType.TYPE_CLASS_TEXT)
                    binding.autocompleteTitle.isClickable = true
                }
                else{

                    binding.autocompleteTitle.showDropDown()
                   binding.autocompleteTitle.setEnabled(true)
                   binding.autocompleteTitle.isCursorVisible = false
                   binding.autocompleteTitle.setInputType(InputType.TYPE_NULL)
                   binding.autocompleteTitle.isClickable = false
                 //  binding.ivSearch.visibility = View.GONE

                }


            }

        binding.ivSearch.setOnClickListener {
              binding.ivSearch.visibility = View.GONE

            binding.autocompleteTitle.text.clear()
            binding.autocompleteTitle.isCursorVisible = false
            binding.autocompleteTitle.setInputType(InputType.TYPE_NULL)
            binding.autocompleteTitle.isClickable = false
            binding.autocompleteTitle.showDropDown()

        }


       /*  binding.autocompleteTitle.setOnTouchListener { v, event ->
             val DRAWABLE_RIGHT = 2

             binding.autocompleteTitle.text.clear()
             binding.autocompleteTitle.showDropDown()
             binding.autocompleteTitle.setEnabled(true)
             binding.autocompleteTitle.isCursorVisible=true
             binding.autocompleteTitle.setInputType(InputType.TYPE_CLASS_TEXT)
             binding.autocompleteTitle.isClickable = true



             if (event.action == MotionEvent.ACTION_UP) {
                 if (event.rawX >= (binding.autocompleteTitle.right - binding.autocompleteTitle.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {

                     return@setOnTouchListener true
                 }
             }
             false
         }*/






        viewModel.CityAPI()
        viewModel.NetworkAPI("")

        setUpViewModelObserver()
        setUpNetworkViewModelObserver()
    }


    private fun setUpViewModelObserver() {
        viewModel.authResult.observe(mActivity) { response ->
            when (response) {
                is AppState.Loading -> {
                    progress.show()
                }

                is AppState.CitySuccess -> {
                    cityList = response.city.City as MutableList<CityX>
                    cityList!!.add(0, CityX("Search by city", "000"))
                    val stateName = cityList!!.map { it.City }

                    // Create ArrayAdapter with shopNames
                    val adapter =
                        ArrayAdapter(requireContext(), R.layout.state_spinner_item, stateName)
                    // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Set adapter to the spinner
                    binding.autocompleteTitle.setAdapter(adapter)
                    progress.dismiss()
                }

                is AppState.NoInternetConnection -> {
                    progress.dismiss()
                    Toast.makeText(mActivity, "Please check your connection", Toast.LENGTH_SHORT)
                        .show()
                }

                is AppState.UnknownError -> {
                    progress.dismiss()
                    Toast.makeText(mActivity, "An error occured", Toast.LENGTH_SHORT).show()
                }

                is AppState.SeverError -> {
                    progress.dismiss()
                    Toast.makeText(mActivity, "ss", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }

        }
    }

    private fun setUpNetworkViewModelObserver() {
        viewModel.networkResult.observe(mActivity) { response ->
            when (response) {
                is AppState.Loading -> {
                    progress.show()
                }

                is AppState.NetworkSuccess -> {
                    progress.dismiss()

                    if (response.network.Citylist.size != 0) {
                        binding.tvHeadings.visibility = View.VISIBLE
                        binding.layoutResult.visibility = View.VISIBLE
                        val announcementAdapter = NetworkAdapter(requireActivity())
                        binding.rvStat.layoutManager =
                            LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvStat.adapter = announcementAdapter
                        announcementAdapter.submitList(response.network.Citylist)
                    }
                }

                is AppState.NoInternetConnection -> {
                    progress.dismiss()
                    Toast.makeText(mActivity, "Please check your connection", Toast.LENGTH_SHORT)
                        .show()
                }

                is AppState.UnknownError -> {
                    progress.dismiss()
                    Toast.makeText(mActivity, "An error occured", Toast.LENGTH_SHORT).show()
                }

                is AppState.SeverError -> {
                    progress.dismiss()
                    Toast.makeText(mActivity, "ss", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }

        }
    }

}