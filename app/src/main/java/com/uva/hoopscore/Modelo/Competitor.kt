package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Competitor(
    @SerializedName("abbreviation")
    val abbreviation: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_code")
    val country_code: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("qualifier")
    val qualifier: String
)