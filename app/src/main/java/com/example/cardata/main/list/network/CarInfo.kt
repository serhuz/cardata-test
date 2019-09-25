package com.example.cardata.main.list.network

data class CarInfo(
    val id: String,
    val imei: String,
    val model: String,
    val manufacturer: String,
    val status: String
) {
    fun enabled() = "activated" == status
}
