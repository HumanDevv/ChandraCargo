package com.chandra.cargo.common



sealed class AppState {
    data object Loading : AppState()
    data object NoInternetConnection : AppState()
    data object UnknownError : AppState()

    data class SeverError(val message: String) : AppState()
    data class APISuccess(val login: String) : AppState()
    //data class APILoginSuccess(val login: LoginResponce) : AppState()
    //data class APILoginSuccess() : AppState()
   // data class APICheckLoginSuccess(val login: CheckLoginResponce) : AppState()




}