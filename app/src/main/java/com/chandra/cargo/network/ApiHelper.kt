package com.chandra.cargo.network

import com.chandra.cargo.base.BaseResponse
import com.chandra.cargo.ui.annoucement.Announcement
import com.chandra.cargo.ui.auth.model.Login
import com.chandra.cargo.ui.auth.model.OTP
import com.chandra.cargo.ui.helpline.model.Helpline
import com.chandra.cargo.ui.network.model.City
import com.chandra.cargo.ui.network.model.Network
import com.chandra.cargo.ui.transaction.model.RecentTransaction
import retrofit2.http.Field


interface ApiHelper {

    suspend fun announcementList(@Field("username") username:String): BaseResponse<Announcement>
    suspend fun loginAccount(@Field("mobile") mobile:String): BaseResponse<Login>
    suspend fun checkLoginOTP(@Field("mobile") mobile:String,@Field("otp") otp:String): BaseResponse<OTP>
    suspend fun recentTransactionList(@Field("userId") userId:String): BaseResponse<RecentTransaction>
    suspend fun helpline_number(@Field("username") username:String): BaseResponse<Helpline>
    suspend fun cityList():BaseResponse<City>

    suspend fun ourNetworks(@Field("cityId") cityId:String): BaseResponse<Network>


}