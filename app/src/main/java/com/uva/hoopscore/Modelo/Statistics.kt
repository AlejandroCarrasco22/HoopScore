package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("totals")
    val totals: Totals
)