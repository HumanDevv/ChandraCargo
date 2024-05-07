package com.chandra.cargo.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.base.BaseViewModel
import com.chandra.cargo.common.AppState
import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.errorHandel.BaseError
import com.chandra.cargo.ui.transaction.model.RecentTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val apiHelper: ApiHelper) : BaseViewModel() {

    private val _transactionResult = MutableLiveData<AppState>()
    val transactionListResult: LiveData<AppState> = _transactionResult

    fun transactionListAPI(userId: String) {
        launchOnBackground {
            _transactionResult.postValue(AppState.Loading)
            val response = apiHelper.recentTransactionList(userId)
            handleTransactionResponse(response)
        }
    }

    private fun handleTransactionResponse(response: BaseResponse<RecentTransaction>) {
        launchOnUI {
        when (response) {
            is BaseResponse.Success -> {
                if (response.data.status) {
                    _transactionResult.postValue(AppState.TransactionSuccess(response.data))

                } else {
                    _transactionResult.postValue(AppState.TransactionSuccess(response.data))

                }
            }
            is BaseResponse.Error -> {
                when (response.error) {
                    is BaseError.UnknownError -> _transactionResult.postValue(AppState.UnknownError)
                    is BaseError.NetworkError -> _transactionResult.postValue(AppState.NoInternetConnection)
                    is BaseError.ServerError -> response.error.responseBody?.let {
                        _transactionResult.postValue(AppState.SeverError(it))
                    }
                    else -> {}
                }
            }
            else -> {}
        }
    }
    }


}
