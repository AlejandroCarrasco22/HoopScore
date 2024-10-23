package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Totals(
    @SerializedName("competitors")
    val competitors: List<CompetitorXX>
)