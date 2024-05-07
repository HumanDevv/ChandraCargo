package com.chandra.cargo.ui.network.model

data class NetworkDetails(
    val CityCounter: Int,
    val Cityname: String,
    val counterList: List<Counter>,
    val status: Boolean
)