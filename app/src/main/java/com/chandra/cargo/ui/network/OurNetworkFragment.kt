package com.chandra.cargo.ui.network

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
import com.google.android.material.internal.ViewUtils.hideKeyboard
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
    var cityList: List<CityX>? = null

    private val viewModel: NetworkViewModel by lazy {
        ViewModelProvider(this)[NetworkViewModel::class.java]
    }
    private lateinit var inputMethodManager: InputMethodManager

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOurNetworkBinding {
        return FragmentOurNetworkBinding.inflate(layoutInflater)
    }

    var stateId: String = ""
    var stateName: String = ""


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = DialogUtils.showProgress(mActivity)
        appPreferences = AppPreferences.getInstance(requireActivity())
        userID = appPreferences.getString(Constant.userId, "")

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text = "Our Networks"
        binding.layoutHeader.ivImg.setImageDrawable(mActivity.resources.getDrawable(R.drawable.network))


        binding.textInputCity.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedCityName = parent.getItemAtPosition(position).toString()
            val selectedCity = cityList?.find { it.City == selectedCityName }
            selectedCity?.let {
                val cityId = it.CityId
                val cityName = it.City
                Log.d("City", "Selected city ID: $cityId, City name: $cityName")
                viewModel.NetworkAPI(cityId)
            }
        }

        binding.textInputCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ) {
                /*   val cityName = binding.textInputCity.text.toString()
                   val selectedCity = cityList?.find { it.City == cityName }
                   selectedCity?.let {
                       val cityId = it.CityId
                       Log.d("City", "Selected city ID: $cityId, City name: $cityName")
                       viewModel.NetworkAPI(cityId)
                   }*/
                hideKeyboard()
                true // Consume the event
            } else {
                false // Let the system handle the event
            }
        }

        inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


        viewModel.CityAPI()
        viewModel.NetworkAPI("")

        setUpViewModelObserver()
        setUpNetworkViewModelObserver()
    }


    // Function to hide the keyboard
    private fun hideKeyboard() {
        val currentFocus = activity?.currentFocus
        currentFocus?.let { view ->
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setUpViewModelObserver() {
        viewModel.authResult.observe(mActivity) { response ->
            when (response) {
                is AppState.Loading -> {
                    progress.show()
                }

                is AppState.CitySuccess -> {
                    cityList = response.city.City


                    val stateName = cityList!!.map { it.City }
                    val adapter = ArrayAdapter(requireContext(), R.layout.state_spinner_item, stateName)
                    // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Set adapter to the spinner
                    binding.textInputCity.setAdapter(adapter)
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
                        binding.rlNoDataFound.visibility = View.GONE
                        val announcementAdapter = NetworkAdapter(requireActivity())
                        binding.rvStat.layoutManager =
                            LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvStat.adapter = announcementAdapter
                        announcementAdapter.submitList(response.network.Citylist)
                    } else {
                        binding.tvHeadings.visibility = View.GONE
                        binding.layoutResult.visibility = View.GONE
                        binding.rlNoDataFound.visibility = View.VISIBLE
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