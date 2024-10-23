package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Coverage(
    @SerializedName("live")
    val live: Boolean,
    @SerializedName("properties")
    val properties: List<Property>
)