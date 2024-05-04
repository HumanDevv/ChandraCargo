package com.chandra.cargo.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chandra.cargo.utils.NetInfo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    @Inject
     lateinit var netInfo: NetInfo
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Handle exceptions here
        Log.e("AppException",throwable.message.toString())
    }

    protected fun launchOnBackground(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler, block = block)
    }

    protected fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Main + exceptionHandler, block = block)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
