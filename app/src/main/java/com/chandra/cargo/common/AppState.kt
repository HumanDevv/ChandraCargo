package com.chandra.cargo.common

import com.chandra.cargo.ui.annoucement.Announcement
import com.chandra.cargo.ui.auth.model.Login
import com.chandra.cargo.ui.auth.model.OTP
import com.chandra.cargo.ui.helpline.model.Helpline
import com.chandra.cargo.ui.network.model.City
import com.chandra.cargo.ui.network.model.Network
import com.chandra.cargo.ui.transaction.model.RecentTransaction


sealed class AppState {
    data object Loading : AppState()
    data object NoInternetConnection : AppState()
    data object UnknownError : AppState()

    data class SeverError(val message: String) : AppState()
    data class APISuccess(val login: String) : AppState()
    data class AnnouncementSuccess(val announcement: Announcement) : AppState()
    data class APILoginSuccess(val login: Login) : AppState()
    data class OTPSuccess(val otp: OTP) : AppState()
    data class TransactionSuccess(val transaction: RecentTransaction) : AppState()
    data class HelplineSuccess(val helpLine: Helpline) : AppState()
    data class CitySuccess(val city: City) : AppState()
    data class NetworkSuccess(val network: Network) : AppState()
   // data class APICheckLoginSuccess(val login: CheckLoginResponce) : AppState()




}