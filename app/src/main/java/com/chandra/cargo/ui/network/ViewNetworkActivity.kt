package com.chandra.cargo.ui.network

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.common.AppState
import com.chandra.cargo.common.DialogUtils
import com.chandra.cargo.databinding.ActivityViewNetworkBinding
import com.chandra.cargo.ui.network.model.CityX
import com.chandra.cargo.ui.network.model.Counter
import com.chandra.cargo.ui.network.model.NetworkDetails
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import com.rudraansh.rudraapay.utils.sharedPrefrence.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewNetworkActivity : BaseActivity<ActivityViewNetworkBinding>() {
    override fun getInjectViewBinding(): ActivityViewNetworkBinding {
        return ActivityViewNetworkBinding.inflate(layoutInflater)
    }
    private lateinit var appPreferences : AppPreferences
    private  lateinit var progress: Dialog
    private  lateinit var userID: String
    private  lateinit var cityName: String
    private  lateinit var cityId: String
    private  lateinit var counter: String
    var counterList: List<Counter>? = null

    private val viewModel: NetworkViewModel by lazy {
        ViewModelProvider(this)[NetworkViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progress= DialogUtils.showProgress(this)
        appPreferences=  AppPreferences.getInstance(this)
        userID=appPreferences.getString(Constant.userId,"")


        cityId=intent.getStringExtra("cityId")!!

        binding.layoutHeader.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.ivImg.visibility= View.GONE
        binding.layoutHeader.tvHeading.text="View Network"

        viewModel.viewNetworkAPI(cityId)
        setUpViewModelObserver()
    }

    override fun setUpViewModelObserver() {
        super.setUpViewModelObserver()
        viewModel.viewnetworkResult.observe(this){response->
            when(response){
                is AppState.Loading ->{
                    progress.show()
                }

                is AppState.NetworkDetailSuccess ->{

                    binding.tvCityName.text="City: "+response.network.Cityname
                    binding.tvCounter.text="Counters: "+response.network.CityCounter
                    if (response.network.counterList.isNotEmpty()){

                        binding.layoutResult.visibility = View.VISIBLE
                        binding.rlNoDataFound.visibility = View.GONE

                        val networkDetailsAdapter = NetworkDetailAdapter(this)
                        binding.rvStat.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        binding.rvStat.adapter = networkDetailsAdapter
                        networkDetailsAdapter.submitList(response.network.counterList)
                    }
                    else{
                        binding.layoutResult.visibility = View.GONE
                        binding.rlNoDataFound.visibility = View.VISIBLE
                    }
                    progress.dismiss()
                }
                is AppState.NoInternetConnection ->{
                    progress.dismiss()
                    Toast.makeText(this, "Please check your connection", Toast.LENGTH_SHORT).show()
                }

                is AppState.UnknownError ->{
                    progress.dismiss()
                    Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show()
                }

                is AppState.SeverError ->{
                    progress.dismiss()
                    Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }

        }
    }

}