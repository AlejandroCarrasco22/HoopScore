package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class SportEvent(
    @SerializedName("competitors")
    val competitors: List<Competitor>,
    @SerializedName("id")
    val id: String,
    @SerializedName("scheduled")
    val scheduled: String,
    @SerializedName("season")
    val season: Season,
    @SerializedName("start_time_tbd")
    val start_time_tbd: Boolean,
    @SerializedName("tournament")
    val tournament: TournamentX,
    @SerializedName("tournament_round")
    val tournament_round: TournamentRound,
    @SerializedName("venue")
    val venue: Venue
)