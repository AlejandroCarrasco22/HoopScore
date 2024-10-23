package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class SportEventX(
    @SerializedName("competitors")
    val competitors: List<CompetitorX>,
    @SerializedName("coverage")
    val coverage: Coverage,
    @SerializedName("id")
    val id: String,
    @SerializedName("sport_event_conditions")
    val sport_event_conditions: SportEventConditions,
    @SerializedName("sport_event_context")
    val sport_event_context: SportEventContext,
    @SerializedName("start_time")
    val start_time: String,
    @SerializedName("start_time_confirmed")
    val start_time_confirmed: Boolean,
    @SerializedName("venue")
    val venue: VenueX
)