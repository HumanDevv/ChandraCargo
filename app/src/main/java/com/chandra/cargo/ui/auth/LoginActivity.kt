package com.chandra.cargo.ui.auth

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.common.AppState
import com.chandra.cargo.common.DialogUtils
import com.chandra.cargo.common.Utils
import com.chandra.cargo.common.Utils.validateNumber
import com.chandra.cargo.databinding.ActivityLoginBinding
import com.chandra.cargo.ui.annoucement.AnnouncementAdapter
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getInjectViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var progress: Dialog

    private val viewModel: AuthViewModel by lazy { ViewModelProvider(this)[AuthViewModel::class.java] }
    private lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("activityLife1","activityLife  onCreate")
        progress= DialogUtils.showProgress(this)
        appPreferences=  AppPreferences.getInstance(this)


        binding.btnLogin.setOnClickListener {
            if (validation()) {
                viewModel.LoginAPI(binding.etMobile.text.toString())

            }
        }

        binding.etMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.startsWith("0", 0, false)) {
              binding.etMobile.text = Editable.Factory.getInstance().newEditable("") } }
            override fun afterTextChanged(s: Editable?) {}
        })
        setUpViewModelObserver()
    }

    private fun validation(): Boolean {
        if (binding.etMobile.text.toString().isNotEmpty()) {
            if (binding.etMobile.text.length != 10) {
                Utils.showToast(this, "Please enter atleast 10 digit")
                return false
            } else {
                if (!validateNumber(binding.etMobile.text.toString())) {
                    Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT)
                        .show()
                    return false
                }
            }
        } else {
            Utils.showToast(this, "Please enter correct number")
            return false
        }
        return true
    }

 override  fun setUpViewModelObserver() {
     super.setUpViewModelObserver()
        viewModel.authResult.observe(this){response->
            when(response){
                is AppState.Loading ->{
                    progress.show()
                }

                is AppState.APILoginSuccess ->{
                    progress.dismiss()
                    Toast.makeText(this,response.login.msg,Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, OtpActivity::class.java)
                    intent.putExtra("mobile",binding.etMobile.text.toString())
                    startActivity(intent)

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

    override fun onStart() {
        Log.d("activityLife1","activityLife  onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("activityLife1","activityLife  onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("activityLife1","activityLife  onStop")
        super.onStop()
    }

    override fun onResume() {
        Log.d("activityLife1","activityLife  onResume")
        super.onResume()
    }

    override fun onRestart() {
        Log.d("activityLife1","activityLife  onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d("activityLife1","activityLife  onDestroy")
        super.onDestroy()
    }
}