package com.chandra.cargo.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandra.cargo.MainActivity
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.databinding.ActivityOtpBinding

class OtpActivity : BaseActivity<ActivityOtpBinding>() {
    override fun getInjectViewBinding(): ActivityOtpBinding {
        return ActivityOtpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

     binding.btnVerify.setOnClickListener {
         val intent= Intent(this,MainActivity::class.java)
         startActivity(intent)
     }
    }
}