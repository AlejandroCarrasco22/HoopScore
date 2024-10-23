package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class GroupX(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)