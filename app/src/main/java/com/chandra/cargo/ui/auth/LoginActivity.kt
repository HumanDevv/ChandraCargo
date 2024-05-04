package com.chandra.cargo.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.common.Utils
import com.chandra.cargo.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getInjectViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
        if (validation()){
            val intent=Intent(this,OtpActivity::class.java)
            startActivity(intent)
        }
        }
    }

    private fun validation(): Boolean {
        if (binding.etMobile.text.toString().isNotEmpty()) {
            if (binding.etMobile.text.length != 10) {
                Utils.showToast(this, "Please enter atleast 10 digit")
                return false
            }
        } else {
            Utils.showToast(this, "Please enter correct number")
            return false
        }
        return true
    }


}