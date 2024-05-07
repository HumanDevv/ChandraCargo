package com.chandra.cargo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chandra.cargo.base.BaseActivity
import com.chandra.cargo.databinding.ActivityMainBinding
import com.chandra.cargo.ui.dashboard.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getInjectViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragment(DashboardFragment())


    }
}