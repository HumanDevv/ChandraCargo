package com.chandra.cargo.ui.transaction.model

data class RecentTransaction(
    val recentTransactionList: List<RecentTransactionX>,
    val status: Boolean
)