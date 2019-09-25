package com.example.cardata.detail.network

data class CarState(
    val imei: String,
    val state: String,
    val latitude: Double,
    val longitude: Double,
    val heading: Int,
    val timestampPosition: String,
    val timestampActive: String,
    val fuelLevelLiters: Double,
    val fuelLevelPercentage: Int
)
