package com.chandra.cargo.network

import com.chandra.cargo.ui.annoucement.Announcement
import com.chandra.cargo.ui.auth.model.Login
import com.chandra.cargo.ui.auth.model.OTP
import com.chandra.cargo.ui.helpline.model.Helpline
import com.chandra.cargo.ui.network.model.City
import com.chandra.cargo.ui.network.model.Network
import com.chandra.cargo.ui.transaction.model.RecentTransaction
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {


    @FormUrlEncoded
    @POST("loginAccount.php")
    suspend fun loginAccount(@Field("mobile") mobile:String): Login
    @FormUrlEncoded
    @POST("checkLoginOtp.php")
    suspend fun checkLoginOTP(@Field("mobile") mobile:String,@Field("otp") otp:String): OTP
    @FormUrlEncoded
    @POST("announcementList.php")
    suspend fun announcementList(@Field("username") username:String): Announcement
    @FormUrlEncoded
    @POST("recentTransactionList.php")
    suspend fun recentTransactionList(@Field("userId") userId:String): RecentTransaction
    @FormUrlEncoded
    @POST("helpline_number.php")
    suspend fun helpline_number(@Field("username") username:String): Helpline
    @POST("cityList.php")
    suspend fun cityList(): City

    @FormUrlEncoded
    @POST("ourNetworks.php")
    suspend fun ourNetworks(@Field("cityId") cityId:String): Network
}