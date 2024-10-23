package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("country_code")
    val country_code: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)