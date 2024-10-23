package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("team_standings")
    val team_standings: List<TeamStanding>
)