package com.chandra.cargo.ui.transaction.model

data class RecentTransactionX(
    val date: String,
    val form: String,
    val markNo: String,
    val payment: String,
    val paymentMode: String,
    val to: String
)