package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Ground(
    @SerializedName("neutral")
    val neutral: Boolean
)