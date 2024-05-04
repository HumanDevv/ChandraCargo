package com.chandra.cargo.network

import com.chandra.cargo.network.ApiHelper
import com.chandra.cargo.network.ApiService
import com.rdd.rdd.common.errorHandel.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiHelperImpl @Inject constructor(private val apiService: ApiService,
                                        private val errorHandler: ErrorHandler
): ApiHelper {


}