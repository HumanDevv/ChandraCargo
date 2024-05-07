package com.chandra.cargo.ui.auth.model

data class Login(
    val mobile: String,
    val msg: String,
    val otp: Int,
    val status: Boolean
)