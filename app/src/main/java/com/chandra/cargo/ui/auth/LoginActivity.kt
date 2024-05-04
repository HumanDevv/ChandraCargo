package com.chandra.cargo.ui.auth

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.common.Utils
import com.chandra.cargo.common.Utils.validateNumber
import com.chandra.cargo.databinding.ActivityLoginBinding
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getInjectViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var progress: Dialog

    private val viewModel: LoginVM by lazy { ViewModelProvider(this)[LoginVM::class.java] }
    private lateinit var appPreferences: AppPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (validation()) {
                val intent = Intent(this, OtpActivity::class.java)
                startActivity(intent)
            }
        }

        binding.etMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.startsWith("0", 0, false)) {
                    binding.etMobile.text = Editable.Factory.getInstance().newEditable("") } }
            override fun afterTextChanged(s: Editable?) {}
        })

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


}