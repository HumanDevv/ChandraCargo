package com.chandra.cargo.ui.auth.model

data class OTP(
    val mobile: String,
    val msg: String,
    val name: String,
    val status: Boolean,
    val userId: String
)