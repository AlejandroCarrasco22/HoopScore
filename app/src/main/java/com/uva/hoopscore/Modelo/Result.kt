package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("sport_event")
    val sport_event: SportEvent,
    @SerializedName("sport_event_status")
    val sport_event_status: SportEventStatus
)