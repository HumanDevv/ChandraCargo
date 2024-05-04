package com.rdd.rdd.common.errorHandel

import com.chandra.cargo.network.errorHandel.BaseError

interface ErrorHandler {
    fun getErrorType(throwable: Throwable): BaseError
}