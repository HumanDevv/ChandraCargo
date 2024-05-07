package com.chandra.cargo.ui.annoucement

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
import com.chandra.cargo.databinding.FragmentAnnouncementBinding
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnnouncementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var announcementList:List<AnnouncementX>
    private lateinit var appPreferences : AppPreferences
    private  lateinit var progress: Dialog

    private val viewModel: AnnouncementViewModel by lazy {
        ViewModelProvider(this)[AnnouncementViewModel::class.java]
    }
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
    ): FragmentAnnouncementBinding {
        return FragmentAnnouncementBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress= DialogUtils.showProgress(mActivity)
        appPreferences=  AppPreferences.getInstance(requireActivity())
        binding.layoutHeader.ivBack.setOnClickListener {
            mActivity.onBackPressedDispatcher.onBackPressed()
        }
        binding.layoutHeader.ivImg.setImageDrawable(mActivity.getDrawable(R.drawable.announcement))

        binding.layoutHeader.tvHeading.text="Announcement"



        viewModel.announcementListAPI("")

        setUpViewModelObserver()
    }
    private fun setUpViewModelObserver() {
        viewModel.announcementListResult.observe(mActivity){response->
            when(response){
                is AppState.Loading ->{
                    progress.show()
                }

                is AppState.AnnouncementSuccess ->{
                    progress.dismiss()
                    announcementList=response.announcement.announcementList
                    val announcementAdapter = AnnouncementAdapter(requireActivity())
                    binding.rvAnnouncement.layoutManager = LinearLayoutManager(mActivity,  LinearLayoutManager.VERTICAL, false)
                    binding.rvAnnouncement.adapter = announcementAdapter
                    announcementAdapter.submitList(announcementList)

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