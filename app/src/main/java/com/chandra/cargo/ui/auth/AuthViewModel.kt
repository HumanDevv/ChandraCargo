package com.chandra.cargo.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.base.BaseViewModel
import com.chandra.cargo.common.AppState
import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.errorHandel.BaseError
import com.chandra.cargo.ui.annoucement.Announcement
import com.chandra.cargo.ui.auth.model.Login
import com.chandra.cargo.ui.auth.model.OTP
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val apiHelper: ApiHelper) : BaseViewModel() {

    private val _authResult = MutableLiveData<AppState>()
    val authResult: LiveData<AppState> = _authResult

    private val _otpResult = MutableLiveData<AppState>()
    val otpResult: LiveData<AppState> = _otpResult

    fun LoginAPI(mobile: String) {
        launchOnBackground {
            _authResult.postValue(AppState.Loading)
            val response = apiHelper.loginAccount(mobile)
            handleLoginResponse(response)
        }
    }

    fun OTPAPI(mobile: String,otp:String) {
        launchOnBackground {
            _otpResult.postValue(AppState.Loading)
            val response = apiHelper.checkLoginOTP(mobile,otp)
            handleOTPResponse(response)
        }
    }

    private fun handleLoginResponse(response: BaseResponse<Login>) {
        launchOnUI {
            when (response) {
                is BaseResponse.Success -> {
                    if (response.data.status) {
                        _authResult.postValue(AppState.APILoginSuccess(response.data))

                    } else {
                        _authResult.postValue(AppState.APILoginSuccess(response.data))

                    }
                }
                is BaseResponse.Error -> {
                    when (response.error) {
                        is BaseError.UnknownError -> _authResult.postValue(AppState.UnknownError)
                        is BaseError.NetworkError -> _authResult.postValue(AppState.NoInternetConnection)
                        is BaseError.ServerError -> response.error.responseBody?.let {
                            _authResult.postValue(AppState.SeverError(it))
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }
    private fun handleOTPResponse(response: BaseResponse<OTP>) {
        launchOnUI {
            when (response) {
                is BaseResponse.Success -> {
                    if (response.data.status) {
                        _otpResult.postValue(AppState.OTPSuccess(response.data))

                    } else {
                        _otpResult.postValue(AppState.OTPSuccess(response.data))

                    }
                }
                is BaseResponse.Error -> {
                    when (response.error) {
                        is BaseError.UnknownError -> _otpResult.postValue(AppState.UnknownError)
                        is BaseError.NetworkError -> _otpResult.postValue(AppState.NoInternetConnection)
                        is BaseError.ServerError -> response.error.responseBody?.let {
                            _otpResult.postValue(AppState.SeverError(it))
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }


}
