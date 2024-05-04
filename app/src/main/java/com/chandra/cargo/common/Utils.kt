package com.chandra.cargo.common

import android.content.Context
import android.widget.Toast

object Utils {
    fun showToast(context: Context, msg:String){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }


    fun validateNumber(number: String): Boolean {
        val regex = Regex("^[^0-5].*")
        return regex.matches(number)
    }

}