package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Property(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: Boolean
)