package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Resultado(
    @SerializedName("generated_at")
    val generated_at: String,
    @SerializedName("sport_event")
    val sport_event: SportEventX,
    @SerializedName("sport_event_status")
    val sport_event_status: SportEventStatus,
    @SerializedName("statistics")
    val statistics: Statistics
)