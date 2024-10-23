package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class SportEventConditions(
    @SerializedName("ground")
    val ground: Ground
)