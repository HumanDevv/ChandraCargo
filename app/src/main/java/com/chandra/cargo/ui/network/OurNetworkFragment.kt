package com.chandra.cargo.ui.network

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
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
import com.chandra.cargo.ui.network.model.CityX
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

    private lateinit var appPreferences : AppPreferences
    private  lateinit var progress: Dialog
    private  lateinit var userID: String
    private  lateinit var networkAdapter: NetworkAdapter
    var cityList: List<CityX>? = null
    private lateinit var adapter: ArrayAdapter<String>

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



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress= DialogUtils.showProgress(mActivity)
        appPreferences=  AppPreferences.getInstance(requireActivity())
        userID=appPreferences.getString(Constant.userId,"")

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text="Our Networks"
        binding.layoutHeader.ivImg.setImageDrawable(mActivity.resources.getDrawable(R.drawable.network))

/*
        binding.autocompleteTitle.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            //   val selectedItem = parent.getItemAtPosition(position) as String
            val selectedState = cityList?.get(position)
            // Handle the selected Campaign object
            Log.d("Spinner", "Selected item: $selectedState")
            // Access the properties of selectedCampaign as needed

            stateId = selectedState!!.CityId
            stateName = selectedState.City
            viewModel.NetworkAPI(stateId)
            setUpViewModelObserver()

        }*/

        // Implement TextWatcher to update suggestions
        binding.textInputCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                // Filter suggestions based on user input
                adapter.filter.filter(editable.toString())
            }
        })
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
        viewModel.CityAPI()
        viewModel.NetworkAPI("")

        setUpViewModelObserver()
        setUpNetworkViewModelObserver()
    }


    private fun setUpViewModelObserver() {
        viewModel.authResult.observe(mActivity){response->
            when(response){
                is AppState.Loading ->{
                    progress.show()
                }

                is AppState.CitySuccess ->{
                    cityList=response.city.City
                    val stateName = cityList!!.map { it.City }
                  //  binding.autocompleteTitle.setAdapter(adapter)
                    adapter = ArrayAdapter(mActivity, android.R.layout.simple_dropdown_item_1line, stateName)

                    // Set adapter to AutoCompleteTextView
                    binding.textInputCity.setAdapter(adapter)

                    progress.dismiss()
                }
                is AppState.NoInternetConnection ->{
                    progress.dismiss()
                    Toast.makeText(mActivity, "Please check your connection", Toast.LENGTH_SHORT).show()
                }

                is AppState.UnknownError ->{
                    progress.dismiss()
                    Toast.makeText(mActivity, "An error occured", Toast.LENGTH_SHORT).show()
                }

                is AppState.SeverError ->{
                    progress.dismiss()
                    Toast.makeText(mActivity, "ss", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }

        }
    }
    private fun setUpNetworkViewModelObserver() {
        viewModel.networkResult.observe(mActivity){response->
            when(response){
                is AppState.Loading ->{
                    progress.show()
                }

                is AppState.NetworkSuccess -> {
                    progress.dismiss()

                    if (response.network.Citylist.size != 0){
                        binding.tvHeadings.visibility = View.VISIBLE
                        binding.layoutResult.visibility = View.VISIBLE
                     networkAdapter = NetworkAdapter(requireActivity())
                    binding.rvStat.layoutManager =
                        LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvStat.adapter = networkAdapter
                    networkAdapter.submitList(response.network.Citylist)
                        networkAdapter.notifyDataSetChanged()

                    }
                    else{
                        binding.tvHeadings.visibility = View.GONE
                        binding.layoutResult.visibility = View.GONE
                        networkAdapter.submitList(emptyList())
                        networkAdapter.notifyDataSetChanged()
                    }
                }
                is AppState.NoInternetConnection ->{
                    progress.dismiss()
                    Toast.makeText(mActivity, "Please check your connection", Toast.LENGTH_SHORT).show()
                }

                is AppState.UnknownError ->{
                    progress.dismiss()
                    Toast.makeText(mActivity, "An error occured", Toast.LENGTH_SHORT).show()
                }

                is AppState.SeverError ->{
                    progress.dismiss()
                    Toast.makeText(mActivity, "ss", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }

        }
    }

}