package com.chandra.cargo.ui.annoucement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.base.BaseViewModel
import com.chandra.cargo.common.AppState
import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.errorHandel.BaseError
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(private val apiHelper: ApiHelper) : BaseViewModel() {

    private val _announcementResult = MutableLiveData<AppState>()
    val announcementListResult: LiveData<AppState> = _announcementResult

    fun announcementListAPI(userId: String) {
        launchOnBackground {
            _announcementResult.postValue(AppState.Loading)
            val response = apiHelper.announcementList(userId)
            handleAnnouncementResponse(response)
        }
    }

    private fun handleAnnouncementResponse(response: BaseResponse<Announcement>) {
        launchOnUI {
        when (response) {
            is BaseResponse.Success -> {
                if (response.data.status) {
                    _announcementResult.postValue(AppState.AnnouncementSuccess(response.data))

                } else {
                    _announcementResult.postValue(AppState.AnnouncementSuccess(response.data))

                }
            }
            is BaseResponse.Error -> {
                when (response.error) {
                    is BaseError.UnknownError -> _announcementResult.postValue(AppState.UnknownError)
                    is BaseError.NetworkError -> _announcementResult.postValue(AppState.NoInternetConnection)
                    is BaseError.ServerError -> response.error.responseBody?.let {
                        _announcementResult.postValue(AppState.SeverError(it))
                    }
                    else -> {}
                }
            }
            else -> {}
        }
    }
    }


}
