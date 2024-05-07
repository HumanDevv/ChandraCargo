package com.chandra.cargo.ui.auth

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chandra.cargo.MainActivity
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.common.AppState
import com.chandra.cargo.common.DialogUtils
import com.chandra.cargo.common.Utils
import com.chandra.cargo.databinding.ActivityOtpBinding
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import com.rudraansh.rudraapay.utils.sharedPrefrence.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpActivity : BaseActivity<ActivityOtpBinding>() {
    override fun getInjectViewBinding(): ActivityOtpBinding {
        return ActivityOtpBinding.inflate(layoutInflater)
    }

    private lateinit var progress: Dialog
    lateinit var mobile:String

    private val viewModel: AuthViewModel by lazy { ViewModelProvider(this)[AuthViewModel::class.java] }
    private lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progress= DialogUtils.showProgress(this)
        appPreferences=  AppPreferences.getInstance(this)
        mobile=intent.getStringExtra("mobile")!!

     binding.btnVerify.setOnClickListener {
         validation()
     }
    }

    private fun validation(){
        val otp =binding.otpView.text.toString()

        if (otp.length!=4){
            Utils.showToast(this,"Please enter otp")
            return
        }
            viewModel.OTPAPI(mobile,otp)
        setUpViewModelObserver()
    }

    override  fun setUpViewModelObserver() {
        super.setUpViewModelObserver()
        viewModel.otpResult.observe(this){response->
            when(response){
                is AppState.Loading ->{
                    progress.show()
                }

                is AppState.OTPSuccess ->{
                    progress.dismiss()
                    if (response.otp.status) {
                        Toast.makeText(this, response.otp.msg, Toast.LENGTH_SHORT).show()
                        appPreferences.saveString(Constant.KEY_ISLOGIN, "true")
                        appPreferences.saveString(Constant.userId, response.otp.userId)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, response.otp.msg, Toast.LENGTH_SHORT).show()

                    }
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