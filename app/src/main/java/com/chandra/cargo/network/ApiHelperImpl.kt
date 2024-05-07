package com.chandra.cargo.network

import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.ApiService
import com.chandra.cargo.ui.annoucement.Announcement
import com.chandra.cargo.ui.auth.model.Login
import com.chandra.cargo.ui.auth.model.OTP
import com.chandra.cargo.ui.helpline.model.Helpline
import com.chandra.cargo.ui.network.model.City
import com.chandra.cargo.ui.network.model.Network
import com.chandra.cargo.ui.network.model.NetworkDetails
import com.chandra.cargo.ui.transaction.model.RecentTransaction
import com.rdd.rdd.common.errorHandel.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiHelperImpl @Inject constructor(private val apiService: ApiService,
                                        private val errorHandler: ErrorHandler
): ApiHelper {
    override suspend fun announcementList(username: String): BaseResponse<Announcement> {
        return try {
            val announcementResult=apiService.announcementList("")
            BaseResponse.Success(announcementResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }

    override suspend fun loginAccount(mobile: String): BaseResponse<Login> {
        return try {
            val loginResult=apiService.loginAccount(mobile)
            BaseResponse.Success(loginResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }

    override suspend fun checkLoginOTP(mobile: String, otp: String): BaseResponse<OTP> {
        return try {
            val otpResult=apiService.checkLoginOTP(mobile,otp)
            BaseResponse.Success(otpResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }

    override suspend fun recentTransactionList(userId: String): BaseResponse<RecentTransaction> {
        return try {
            val transactionResult=apiService.recentTransactionList(userId)
            BaseResponse.Success(transactionResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }    }

    override suspend fun helpline_number(userId: String): BaseResponse<Helpline> {
        return try {
            val helpLineResult=apiService.helpline_number(userId)
            BaseResponse.Success(helpLineResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }

    override suspend fun cityList(): BaseResponse<City> {
        return try {
            val helpLineResult=apiService.cityList()
            BaseResponse.Success(helpLineResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }    }

    override suspend fun ourNetworks(cityId: String): BaseResponse<Network> {
        return try {
            val helpLineResult=apiService.ourNetworks(cityId)
            BaseResponse.Success(helpLineResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }

    override suspend fun ourNetworksDetails(cityId: String): BaseResponse<NetworkDetails> {
        return try {
            val helpLineResult=apiService.ourNetworkDetails(cityId)
            BaseResponse.Success(helpLineResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }

    override suspend fun grievance(
        userId: String,
        subject: String,
        title: String,
        message: String
    ): BaseResponse<CommonResponse> {
        return try {
            val helpLineResult=apiService.grievance(userId, subject, title, message)
            BaseResponse.Success(helpLineResult)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }


}