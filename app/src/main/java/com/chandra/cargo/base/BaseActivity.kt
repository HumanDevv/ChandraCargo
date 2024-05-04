package com.chandra.cargo.base

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chandra.cargo.R


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding : VB
    abstract fun getInjectViewBinding(): VB
    open fun setUpViewModelObserver() {}
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getInjectViewBinding()
        setContentView(binding.root)
        progressBar = getProgressBar()

    }
    open fun getProgressBar(): ProgressBar? {
        return null
    }
    fun replaceFragment(fragment: Fragment) {
        replaceFragment(false, fragment)
    }

    fun replaceFragment(isAdded:Boolean,fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        //  transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
        // Check if the fragment is already added
        val existingFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (existingFragment != null && existingFragment.javaClass.simpleName == fragment.javaClass.simpleName) {
            // If the fragment is already added, do nothing
            return
        }
        if (isAdded)
        {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.commit()
    }

    fun replaceFragment(isAdded:Boolean,fragment: Fragment, isOfficial:Int) {
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt("isOfficial", isOfficial)
        fragment.arguments=bundle
        //  transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
        // Check if the fragment is already added
        val existingFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (existingFragment != null && existingFragment.javaClass.simpleName == fragment.javaClass.simpleName) {
            // If the fragment is already added, do nothing
            return
        }
        if (isAdded) {

            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.commit()
    }
}
