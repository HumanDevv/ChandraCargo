package com.chandra.cargo.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.chandra.cargo.interfaces.DialogClickInterface
import com.chandra.cargo.R
import com.chandra.cargo.databinding.LayoutLogoutDialogBinding

class DialogUtils {

    companion object {
       private lateinit var mDialog: Dialog

        fun showAlert(context: Context, title: String, message: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        fun showProgress(context: Context): Dialog {
            mDialog = Dialog(context)
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog.setCancelable(false)
            mDialog!!.setContentView(R.layout.custom_progress_bar)
            val window: Window = mDialog!!.window!!
            mDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            return mDialog
        }
        fun logoutDialog(context: Context, mCallBack: DialogClickInterface){
            mDialog = Dialog(context, R.style.Theme_Dialog)
            mDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog?.setCancelable(false)
            mDialog?.setCanceledOnTouchOutside(false)
            val binding = LayoutLogoutDialogBinding.inflate(
                LayoutInflater.from(context),
                null,
                false
            )
            mDialog?.setContentView(binding.root)
            binding.btnLogout.setOnClickListener {
                mDialog?.dismiss()
                mCallBack.sureClickEvent()
            }
            binding.btnCancel.setOnClickListener {
                mDialog?.dismiss()
            }
            mDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mDialog?.show()
        }



    }
}