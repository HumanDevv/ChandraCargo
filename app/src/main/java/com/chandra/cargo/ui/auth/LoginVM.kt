package com.chandra.cargo.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.base.BaseViewModel
import com.chandra.cargo.common.AppState
import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.errorHandel.BaseError
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginVM @Inject constructor(private val apiHelper: ApiHelper) : BaseViewModel(){
    private val _loginResult = MutableLiveData<AppState>()
    val loginResult: LiveData<AppState> = _loginResult

    fun login(number: String, password: String) {
        launchOnBackground {
            // val loginRequest = LoginRequest(number,password,empType)
            //val response = apiHelper.login(loginRequest)
         //   val response = apiHelper.login(number,password)
         //   handleLoginResponse(response)
        }
    }

  /*  private fun handleLoginResponse(response: BaseResponse<LoginResponce>) {
        launchOnUI {
            when (response) {
                is BaseResponse.Success -> {
                    if (response.data.status == false) {
                      //  response.data.msg?.let { _loginResult.postValue(AppState.SeverError(it)) }
                    }
                    else {
                        _loginResult.postValue(AppState.APILoginSuccess())
                        //_loginResult.postValue(AppState.APILoginSuccess(response.data))
                        //   _loginResult.postValue(AppState.APISuccess(response.data))
                    }
                }
                is BaseResponse.Error -> {
                    when (response.error) {
                        is BaseError.UnknownError -> _loginResult.postValue(AppState.UnknownError)
                        is BaseError.NetworkError -> _loginResult.postValue(AppState.NoInternetConnection)
                        is BaseError.ServerError -> response.error.responseBody?.let {
                            _loginResult.postValue(AppState.SeverError(it))
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }*/
}
