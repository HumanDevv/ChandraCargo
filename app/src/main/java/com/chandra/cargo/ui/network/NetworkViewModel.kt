package com.chandra.cargo.ui.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.base.BaseViewModel
import com.chandra.cargo.common.AppState
import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.errorHandel.BaseError
import com.chandra.cargo.ui.network.model.City
import com.chandra.cargo.ui.network.model.Network
import com.chandra.cargo.ui.network.model.NetworkDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val apiHelper: ApiHelper) : BaseViewModel() {

    private val _authResult = MutableLiveData<AppState>()
    val authResult: LiveData<AppState> = _authResult

    private val _networkResult = MutableLiveData<AppState>()
    val networkResult: LiveData<AppState> = _networkResult

    private val _viewnetworkResult = MutableLiveData<AppState>()
    val viewnetworkResult: LiveData<AppState> = _viewnetworkResult

    fun CityAPI() {
        launchOnBackground {
            _authResult.postValue(AppState.Loading)
            val response = apiHelper.cityList()
            handleCityResponse(response)
        }
    }

    fun NetworkAPI(cityId:String) {
        launchOnBackground {
            _authResult.postValue(AppState.Loading)
            val response = apiHelper.ourNetworks(cityId)
            handleNetworkResponse(response)
        }
    }


    fun viewNetworkAPI(cityId:String) {
        launchOnBackground {
            _viewnetworkResult.postValue(AppState.Loading)
            val response = apiHelper.ourNetworksDetails(cityId)
            handleNetworkDetailsResponse(response)
        }
    }


    private fun handleCityResponse(response: BaseResponse<City>) {
        launchOnUI {
            when (response) {
                is BaseResponse.Success -> {
                    if (response.data.status) {
                        _authResult.postValue(AppState.CitySuccess(response.data))

                    } else {
                        _authResult.postValue(AppState.CitySuccess(response.data))

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
    private fun handleNetworkResponse(response: BaseResponse<Network>) {
        launchOnUI {
            when (response) {
                is BaseResponse.Success -> {
                    if (response.data.status) {
                        _networkResult.postValue(AppState.NetworkSuccess(response.data))

                    } else {
                        _networkResult.postValue(AppState.NetworkSuccess(response.data))
                    }
                }
                is BaseResponse.Error -> {
                    when (response.error) {
                        is BaseError.UnknownError -> _networkResult.postValue(AppState.UnknownError)
                        is BaseError.NetworkError -> _networkResult.postValue(AppState.NoInternetConnection)
                        is BaseError.ServerError -> response.error.responseBody?.let {
                            _networkResult.postValue(AppState.SeverError(it))
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }
    private fun handleNetworkDetailsResponse(response: BaseResponse<NetworkDetails>) {
        launchOnUI {
            when (response) {
                is BaseResponse.Success -> {
                    if (response.data.status) {
                        _viewnetworkResult.postValue(AppState.NetworkDetailSuccess(response.data))

                    } else {
                        _viewnetworkResult.postValue(AppState.NetworkDetailSuccess(response.data))
                    }
                }
                is BaseResponse.Error -> {
                    when (response.error) {
                        is BaseError.UnknownError -> _viewnetworkResult.postValue(AppState.UnknownError)
                        is BaseError.NetworkError -> _viewnetworkResult.postValue(AppState.NoInternetConnection)
                        is BaseError.ServerError -> response.error.responseBody?.let {
                            _viewnetworkResult.postValue(AppState.SeverError(it))
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }


}
