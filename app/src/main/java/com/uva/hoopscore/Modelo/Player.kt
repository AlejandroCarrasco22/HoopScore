package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("statistics")
    val statistics: StatisticsX
)