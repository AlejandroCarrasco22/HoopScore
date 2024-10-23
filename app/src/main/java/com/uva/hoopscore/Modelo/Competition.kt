package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Competition(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)