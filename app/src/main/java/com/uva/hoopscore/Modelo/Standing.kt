package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("groups")
    val groups: List<Group>,
    @SerializedName("tie_break_rule")
    val tie_break_rule: String,
    @SerializedName("type")
    val type: String
)