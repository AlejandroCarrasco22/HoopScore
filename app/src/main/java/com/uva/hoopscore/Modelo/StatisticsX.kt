package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class StatisticsX(
    @SerializedName("assists")
    val assists: Int,
    @SerializedName("blocks")
    val blocks: Int,
    @SerializedName("defensive_rebounds")
    val defensive_rebounds: Int,
    @SerializedName("field_goals_attempted")
    val field_goals_attempted: Int,
    @SerializedName("field_goals_made")
    val field_goals_made: Int,
    @SerializedName("free_throws_attempted")
    val free_throws_attempted: Int,
    @SerializedName("free_throws_made")
    val free_throws_made: Int,
    @SerializedName("minutes")
    val minutes: String,
    @SerializedName("offensive_rebounds")
    val offensive_rebounds: Int,
    @SerializedName("personal_fouls")
    val personal_fouls: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("steals")
    val steals: Int,
    @SerializedName("technical_fouls")
    val technical_fouls: Int,
    @SerializedName("three_pointers_attempted")
    val three_pointers_attempted: Int,
    @SerializedName("three_pointers_made")
    val three_pointers_made: Int,
    @SerializedName("total_rebounds")
    val total_rebounds: Int,
    @SerializedName("turnovers")
    val turnovers: Int
)