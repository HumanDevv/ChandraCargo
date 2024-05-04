package com.chandra.cargo.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.chandra.cargo.MainActivity
import com.chandra.cargo.R
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.databinding.ActivitySplashBinding
import com.chandra.cargo.ui.auth.LoginActivity
import com.rdd.rdd.utils.sharedPrefrence.AppPreferences
import com.rudraansh.rudraapay.utils.sharedPrefrence.Constant.KEY_ISLOGIN

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val SPLASH_DELAY: Long = 3000 // 3 seconds
    private lateinit var appPreferences : AppPreferences
    var isLogin:String?=null

    override fun getInjectViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        appPreferences=  AppPreferences.getInstance(this)
        isLogin = appPreferences.getString(KEY_ISLOGIN, "")
        delay()

    }

    private fun delay(){
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an Intent to start the main activity
            if (isLogin=="true"){
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                // Close this activity to prevent going back to the splash screen
                finish()
            }
            else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                // Close this activity to prevent going back to the splash screen
                finish()
            }

        }, SPLASH_DELAY)

    }

}