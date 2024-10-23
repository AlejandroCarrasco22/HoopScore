package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class SportEventContext(
    @SerializedName("category")
    val category: Category,
    @SerializedName("competition")
    val competition: Competition,
    @SerializedName("groups")
    val groups: List<GroupX>,
    @SerializedName("round")
    val round: Round,
    @SerializedName("season")
    val season: SeasonX,
    @SerializedName("sport")
    val sport: Sport,
    @SerializedName("stage")
    val stage: Stage
)