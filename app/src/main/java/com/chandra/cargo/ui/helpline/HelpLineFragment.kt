package com.chandra.cargo.ui.helpline

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseFragment
import com.chandra.cargo.common.AppState
import com.chandra.cargo.common.DialogUtils
import com.chandra.cargo.databinding.FragmentHelpLineBinding
import com.chandra.cargo.ui.transaction.RecentTransactionAdapter
import com.chandra.cargo.ui.transaction.TransactionViewModel
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import com.rudraansh.rudraapay.utils.sharedPrefrence.Constant
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HelpLineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HelpLineFragment : BaseFragment<FragmentHelpLineBinding>() {

    private lateinit var appPreferences : AppPreferences
    private  lateinit var progress: Dialog
    private  lateinit var userID: String

    private val viewModel: HelpLineViewModel by lazy {
        ViewModelProvider(this)[HelpLineViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHelpLineBinding {
        return FragmentHelpLineBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress= DialogUtils.showProgress(mActivity)
        appPreferences=  AppPreferences.getInstance(requireActivity())
        userID=appPreferences.getString(Constant.userId,"")

        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.tvHeading.text="Helpline and Grievance"
        binding.layoutHeader.ivImg.setImageDrawable(mActivity.getDrawable(R.drawable.helpline))

        binding.layoutHelpLine.visibility=View.VISIBLE
        binding.layoutGrievanceForm.visibility=View.GONE
        binding.cvHelpline.strokeColor=mActivity.resources.getColor(R.color.purple,null)
        binding.cvHelpline.strokeWidth=7
        binding.cvGrievance.strokeColor=mActivity.resources.getColor(R.color.purple,null)
        binding.cvGrievance.strokeWidth=3

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

            viewModel.helpLineAPI("")
            setUpViewModelObserver()

    }

    private fun setUpViewModelObserver() {
        viewModel.helpLineResult.observe(mActivity){response->
            when(response){
                is AppState.Loading ->{
                    progress.show()
                }

                is AppState.HelplineSuccess ->{
                    binding.tvHelpLineNo.text=response.helpLine.helplineList[0].mobileNo
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

}