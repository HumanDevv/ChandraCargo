package com.chandra.cargo.ui.helpline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.base.BaseViewModel
import com.chandra.cargo.common.AppState
import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.CommonResponse
import com.chandra.cargo.network.errorHandel.BaseError
import com.chandra.cargo.ui.helpline.model.Helpline
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HelpLineViewModel @Inject constructor(private val apiHelper: ApiHelper) : BaseViewModel() {

    private val _helpLineResult = MutableLiveData<AppState>()
    val helpLineResult: LiveData<AppState> = _helpLineResult

    private val _grievanceResult = MutableLiveData<AppState>()
    val grievanceResult: LiveData<AppState> = _grievanceResult

    fun helpLineAPI(userId: String) {
        launchOnBackground {
            _helpLineResult.postValue(AppState.Loading)
            val response = apiHelper.helpline_number(userId)
            handleAnnouncementResponse(response)
        }
    }

    private fun handleAnnouncementResponse(response: BaseResponse<Helpline>) {
        launchOnUI {
        when (response) {
            is BaseResponse.Success -> {
                if (response.data.status) {
                    _helpLineResult.postValue(AppState.HelplineSuccess(response.data))

                } else {
                    _helpLineResult.postValue(AppState.HelplineSuccess(response.data))

                }
            }
            is BaseResponse.Error -> {
                when (response.error) {
                    is BaseError.UnknownError -> _helpLineResult.postValue(AppState.UnknownError)
                    is BaseError.NetworkError -> _helpLineResult.postValue(AppState.NoInternetConnection)
                    is BaseError.ServerError -> response.error.responseBody?.let {
                        _helpLineResult.postValue(AppState.SeverError(it))
                    }
                    else -> {}
                }
            }
            else -> {}
        }
    }
    }



    fun grievanceAPI(userId: String,
                       subject: String,
                       title: String,
                       message: String) {
        launchOnBackground {
            _grievanceResult.postValue(AppState.Loading)
            val response = apiHelper.grievance(userId, subject, title, message)
            handleGrievanceResponse(response)
        }
    }

    private fun handleGrievanceResponse(response: BaseResponse<CommonResponse>) {
        launchOnUI {
            when (response) {
                is BaseResponse.Success -> {
                    if (response.data.status) {
                        _grievanceResult.postValue(AppState.GrievanceSuccess(response.data))

                    } else {
                        _grievanceResult.postValue(AppState.GrievanceSuccess(response.data))

                    }
                }
                is BaseResponse.Error -> {
                    when (response.error) {
                        is BaseError.UnknownError -> _grievanceResult.postValue(AppState.UnknownError)
                        is BaseError.NetworkError -> _grievanceResult.postValue(AppState.NoInternetConnection)
                        is BaseError.ServerError -> response.error.responseBody?.let {
                            _grievanceResult.postValue(AppState.SeverError(it))
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }


}
